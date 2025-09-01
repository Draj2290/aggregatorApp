package personal.project.aggregator;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.websocket.DeploymentException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class AggregatorApplication {


	public static void main(String[] args) throws DeploymentException, IOException, InterruptedException {

		SpringApplication.run(AggregatorApplication.class, args);
	}

}
