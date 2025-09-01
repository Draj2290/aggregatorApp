package personal.project.aggregator.models;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
public class RSSSubscriptions {

    private Map<String,String> subscriptions;
}
