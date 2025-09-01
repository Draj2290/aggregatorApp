package personal.project.aggregator.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChatFeed {

    @JsonProperty
    String url;

    @JsonProperty
    String description;

}
