package personal.project.aggregator.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import personal.project.aggregator.entities.Subscription;
import personal.project.aggregator.repository.SubscriptionRepository;

import javax.sql.DataSource;
@Configuration
@EnableJpaRepositories(basePackageClasses = SubscriptionRepository.class,
        entityManagerFactoryRef = "localVideoTM"
)
public class DBConfig {

    @Bean
    DataSource h2DataSource(){
        return DataSourceBuilder.create().url("jdbc:h2:mem:testdb")
                .driverClassName("org.h2.Driver")
                .username("sa")
                .password("password")
                .build();
    }


    @Bean(name="localVideoTM")
    public LocalContainerEntityManagerFactoryBean localVideoTM(@Qualifier("h2DataSource") DataSource dataSource, EntityManagerFactoryBuilder builder){
        return builder.dataSource(dataSource)
                .packages("personal.project.aggregator.entities")
                .build();

    }

//    @Bean(name="jpaTransactionManager")
//    public JpaTransactionManager jpaTransactionManager(@Qualifier("localVideoTM") LocalContainerEntityManagerFactoryBean e){
//        JpaTransactionManager jpa= new JpaTransactionManager();
//        jpa.setEntityManagerFactory(e.getObject());
//        return jpa;
//    }

}
