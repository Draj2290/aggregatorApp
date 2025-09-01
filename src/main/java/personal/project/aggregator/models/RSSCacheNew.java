package personal.project.aggregator.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Component
public class RSSCacheNew {

    List<RSSFeedNew> RSSForThisNews;

}
