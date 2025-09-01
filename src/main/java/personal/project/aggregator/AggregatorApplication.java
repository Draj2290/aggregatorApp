package personal.project.aggregator;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.websocket.DeploymentException;
import org.springframework.ai.model.openai.autoconfigure.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateFactorySpi;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(exclude = {org.springframework.ai.model.openai.autoconfigure.OpenAiAudioSpeechAutoConfiguration.class,
		org.springframework.ai.model.openai.autoconfigure.OpenAiEmbeddingAutoConfiguration.class,
		org.springframework.ai.model.openai.autoconfigure.OpenAiImageAutoConfiguration.class,
		org.springframework.ai.model.openai.autoconfigure.OpenAiModerationAutoConfiguration.class,
		org.springframework.ai.model.openai.autoconfigure.OpenAiAudioTranscriptionAutoConfiguration.class,
		org.springframework.ai.model.openai.autoconfigure.OpenAiChatAutoConfiguration.class
		})
public class AggregatorApplication {


	public static String secretkey="InfinitiQ#50";
	public static void main(String[] args) throws DeploymentException, IOException, InterruptedException {
		SpringApplication.run(AggregatorApplication.class, args);
	}

}
