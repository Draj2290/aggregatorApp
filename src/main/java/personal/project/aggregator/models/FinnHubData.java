package personal.project.aggregator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinnHubData {

    @JsonProperty
    String s;

    @JsonProperty
    Double p;

    @JsonProperty
    Long t;

    @JsonProperty
    Double v;
}
