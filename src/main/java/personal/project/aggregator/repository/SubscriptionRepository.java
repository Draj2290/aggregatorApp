package personal.project.aggregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.project.aggregator.entities.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
}
