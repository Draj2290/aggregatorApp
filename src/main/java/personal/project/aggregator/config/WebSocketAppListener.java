//package personal.project.aggregator.config;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
//import org.springframework.web.reactive.socket.client.WebSocketClient;
//import personal.project.aggregator.models.StockSubscription;
//import reactor.core.publisher.Mono;
//import java.net.URI;
//
//@Slf4j
//@Component
//public class WebSocketAppListener implements ApplicationListener<WebSocketEvent> {
//
//    @Value("${websocket.endpoint.finnhub.api}")
//    String finnHubUrl;
//
//    @Value("${websocket.key.finnhub.api}")
//    String finnHubKey;
//
//
//     @Override
//    public void onApplicationEvent(WebSocketEvent event) {
//        WebSocketClient socketClient=new ReactorNettyWebSocketClient();
//
//        StockSubscription subscription=new StockSubscription();
//        subscription.setSymbol(event.getMessage());
//        subscription.setType("subscribe");
//        ObjectMapper objectMapper=new ObjectMapper();
//
//        socketClient.execute(URI.create(finnHubUrl+"?token="+finnHubKey),(session)->{
//            try {
//                log.info("Sent event to FinnHub for subscription");
//                return session.send(Mono.just(session.textMessage(objectMapper.writeValueAsString(subscription)))).then();
//            } catch (JsonProcessingException e) {
//                log.error("Failed to initialize subscription to FinnHub");
//                throw new RuntimeException(e);
//            }
//        }).subscribe();
//    }
//}
