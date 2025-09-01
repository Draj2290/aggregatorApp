package personal.project.aggregator.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChatGptResponse {

    @JsonProperty("feed")
    List<ChatFeed> choices;
}
