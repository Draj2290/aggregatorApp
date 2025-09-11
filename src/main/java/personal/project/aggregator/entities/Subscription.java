package personal.project.aggregator.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="subscription")
@Table(name="subscription")
@NoArgsConstructor
public class Subscription {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    String name;

    String url;

    public Subscription(String name,String url)
    {
        this.name=name;
        this.url=url;
    }
}
