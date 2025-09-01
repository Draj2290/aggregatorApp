package personal.project.aggregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncConfig {

    ExecutorService service(){
        return Executors.newFixedThreadPool(10);
    }
}
