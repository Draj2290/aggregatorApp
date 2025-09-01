package personal.project.aggregator.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StockSubscription {

    @JsonProperty
    String type;

    @JsonProperty
    String symbol;
}
