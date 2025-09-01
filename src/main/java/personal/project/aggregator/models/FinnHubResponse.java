package personal.project.aggregator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FinnHubResponse {

    @JsonProperty
    String type;


    @JsonProperty
    List<FinnHubData> data;
}
