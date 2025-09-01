package personal.project.aggregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import personal.project.aggregator.models.StockTickers;

import java.time.Duration;

@Configuration
@EnableWebMvc
@EnableScheduling
public class GetViews {


    @Bean
    ClassLoaderTemplateResolver classLoaderTemplateResolver(){
        ClassLoaderTemplateResolver classLoaderTemplateResolver=new ClassLoaderTemplateResolver();
        classLoaderTemplateResolver.setPrefix("templates/");
        classLoaderTemplateResolver.setSuffix(".html");
        classLoaderTemplateResolver.setOrder(1);
        classLoaderTemplateResolver.setTemplateMode(TemplateMode.HTML);
        return classLoaderTemplateResolver;
    }




    @Bean
    SpringTemplateEngine TemplateEngine(){
        SpringTemplateEngine springTemplateEngine=new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(classLoaderTemplateResolver());
        springTemplateEngine.setEnableSpringELCompiler(true);
        return springTemplateEngine;
    }

    @Bean
    ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver viewResolver=new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(TemplateEngine());
        viewResolver.setContentType("text/html");
        return viewResolver;
    }


}
