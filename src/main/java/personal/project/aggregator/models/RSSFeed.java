package personal.project.aggregator.models;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Data
public class RSSFeed {
    private String imgSrc;
    private String description;
    private String href;
    private String title;
}
