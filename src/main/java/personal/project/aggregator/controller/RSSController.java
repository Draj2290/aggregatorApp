package personal.project.aggregator.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.InputSource;
import personal.project.aggregator.entities.Subscription;
import personal.project.aggregator.models.*;
import personal.project.aggregator.repository.SubscriptionRepository;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class RSSController {

    @Autowired
    RSSCacheNew rssCacheNew;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    private static final String promptText="Give me RSS Feed suggestions with only the URL and description in JSON format such as {\"feed\":[{\"url\":\"https://feed...\",\"description\":\"text\"}]} with description or tags: <descriptionOrTags> (Only send me links which actually lead to RSS feed document not an HTML document";
    @Autowired
    OpenAiChatModel chatModel;

    public InputStream getInputStream(String URL) throws IOException, URISyntaxException {
        URLConnection urlConnection = new URI(URL).toURL().openConnection();
        return urlConnection.getInputStream();
    }

    @PostConstruct
    public void loadDefaultFeeds(){
        rssCacheNew.setRSSForThisNews(new ArrayList<RSSFeedNew>());
        Map<String,String> defaultSubs=new HashMap<>();
        defaultSubs.put("CNBC","https://search.cnbc.com/rs/search/combinedcms/view.xml?partnerId=wrss01&id=100003114");
        defaultSubs.put("MarketWatch","https://feeds.content.dowjones.io/public/rss/mw_topstories");

        defaultSubs.forEach((k,v)->{
            subscriptionRepository.save(new Subscription(k,v));
            try(InputStream inputStream=getInputStream(v)) {

                SyndFeedInput syndFeedInput1=new SyndFeedInput();
                SyndFeed currentFeed=syndFeedInput1.build(new InputSource(inputStream));
                ArrayList<RSSFeed> listOfFeedsForK=new ArrayList<>();

                currentFeed.getEntries().forEach(s->{
                    RSSFeed entry=new RSSFeed();
                    entry.setHref(s.getLink());
                    entry.setTitle(s.getTitle());
                    SyndContent syndContent=s.getDescription();
                    entry.setDescription(syndContent!=null?syndContent.getValue():s.getTitle());
                    listOfFeedsForK.add(entry);
                });
                if(rssCacheNew.getRSSForThisNews()==null) {
                    rssCacheNew.setRSSForThisNews(List.of(new RSSFeedNew(k, listOfFeedsForK)));
                }else {
                    rssCacheNew.getRSSForThisNews().add(new RSSFeedNew(k, listOfFeedsForK));
                }

            } catch (IOException e) {
                log.error(e.getMessage());
            } catch (URISyntaxException e) {
                log.error(e.getMessage());
            } catch (FeedException e) {
                log.error(e.getMessage());
            }
        });
    }


    public void loadRSSCache(){
        rssCacheNew.setRSSForThisNews(new ArrayList<RSSFeedNew>());
        List<Subscription> subscriptions=subscriptionRepository.findAll();
        subscriptions.forEach(subscription->{
            try(InputStream inputStream=getInputStream(subscription.getUrl())) {

                SyndFeedInput syndFeedInput1=new SyndFeedInput();
                SyndFeed currentFeed=syndFeedInput1.build(new InputSource(inputStream));
                ArrayList<RSSFeed> listOfFeedsForK=new ArrayList<>();

                currentFeed.getEntries().forEach(s->{
                    RSSFeed entry=new RSSFeed();
                    entry.setHref(s.getLink());
                    entry.setTitle(s.getTitle());
                    SyndContent syndContent=s.getDescription();
                    entry.setDescription(syndContent!=null?syndContent.getValue():s.getTitle());
                    listOfFeedsForK.add(entry);
                });
                if(rssCacheNew.getRSSForThisNews()==null) {
                    rssCacheNew.setRSSForThisNews(List.of(new RSSFeedNew(subscription.getName(), listOfFeedsForK)));
                }else {
                    rssCacheNew.getRSSForThisNews().add(new RSSFeedNew(subscription.getName(), listOfFeedsForK));
                }

            } catch (IOException e) {
                log.error(e.getMessage());
            } catch (URISyntaxException e) {
                log.error(e.getMessage());
            } catch (FeedException e) {
                log.error(e.getMessage());
            }
        });
    }

    @GetMapping(value={"feed"},produces={"application/json"})
    public ResponseEntity<String> feed() throws JsonProcessingException {
        loadRSSCache();
        System.out.println("Loaded cache");
        ObjectMapper objectMapper=new ObjectMapper();
        return ResponseEntity.ok(objectMapper.writeValueAsString(rssCacheNew));
    }

    @GetMapping(value="subscriptions",produces={"application/json"})
    public List<Subscription> rssSubscriptionList(){
        List<Subscription> rssSubscriptionList=subscriptionRepository.findAll();
        return rssSubscriptionList;
    }


    @PostMapping(value="getsuggestions",produces="application/json")
    public ChatGptResponse getSuggestions(@RequestBody String body) throws JsonProcessingException {
        ChatClient chatClient= ChatClient.builder(chatModel).build();

        String output=chatClient.prompt().user(spec->{
                    spec.text(RSSController.promptText);
                    spec.param("descriptionOrTags",body);
                }).templateRenderer(StTemplateRenderer.builder().startDelimiterToken('<')
                        .endDelimiterToken('>')
                        .build())
                .call().chatResponse().getResult().getOutput().getText();
        ChatSuggestion suggestion=new ChatSuggestion();
        suggestion.setText("");
        System.out.println("Output from OpenAI: "+output);

        output=output.replace("json","").replaceAll("```","");
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ChatGptResponse chatGptResponse=objectMapper.readValue(output,ChatGptResponse.class);

         return chatGptResponse;
    }




    @PostMapping(value="addsubscription",produces="application/json")
    public String addSubscription(@RequestParam("title") String title, @RequestParam("href") String href ) {
        subscriptionRepository.save(new Subscription(title,href));
        return "{\"message\":\"Successfully added\"}";
    }

}
