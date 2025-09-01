package personal.project.aggregator.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
public class Suggestion {

    @JsonProperty("url")
    String url;

    @JsonProperty("description")
    String description;

}
