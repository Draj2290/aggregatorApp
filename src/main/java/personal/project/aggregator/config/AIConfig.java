package personal.project.aggregator.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Base64;

@Slf4j
@Configuration
public class AIConfig {

    @Value("${string-of-ai}")
    String aiString;

    @Bean
    OpenAiChatModel openAiChatModel(){
        try(FileInputStream fis=new FileInputStream("src/main/resources/EncDec.jks")) {
            Cipher cipher = Cipher.getInstance("RSA");
            System.out.println(aiString);
            KeyStore ks=KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(fis,"InfinitiQ#50".toCharArray());
            Key certificate=ks.getKey("EncryptAPI","InfinitiQ#50".toCharArray());

            cipher.init(Cipher.DECRYPT_MODE,certificate);
            byte[] openAi=cipher.doFinal(Base64.getDecoder().decode(aiString.getBytes(StandardCharsets.UTF_8)));
            return OpenAiChatModel.builder().openAiApi(OpenAiApi.builder()
                    .apiKey(new String(openAi))
                    .build()).build();
        }catch(Exception e){
            log.error(e.getMessage());
            for(StackTraceElement s:e.getStackTrace()) {
                log.error(s.toString());
            }
            return null;
        }

    }

}
