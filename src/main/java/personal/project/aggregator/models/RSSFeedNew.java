package personal.project.aggregator.models;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@AllArgsConstructor
@Data
public class RSSFeedNew {
    String source;
    List<RSSFeed> feeds;
}
