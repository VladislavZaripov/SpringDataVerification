package configuration;

import entity.Genre;
import entity.SingerThree;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan({"service", "entity"})
public class ConfigurationClassForAnnotation {

    @Bean
    LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public SingerThree mayer() throws Exception {
        SingerThree singerThree = new SingerThree();
        singerThree.setFirstName("J");
        singerThree.setLastName("Mayer");
        singerThree.setGender(null);
        singerThree.setGenre(null);
        return singerThree;
    }

    @Bean
    public SingerThree countrySinger() throws Exception {
        SingerThree singerThree = new SingerThree();
        singerThree.setFirstName("John");
        singerThree.setLastName("Mayer");
        singerThree.setGender(null);
        singerThree.setGenre(Genre.COUNTRY);
        return singerThree;
    }
}