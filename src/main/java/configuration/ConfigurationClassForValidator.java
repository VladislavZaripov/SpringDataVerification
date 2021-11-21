package configuration;

import entity.SingerOne;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "validators")
public class ConfigurationClassForValidator {

    @Bean
    public SingerOne john() throws Exception {
        SingerOne singerOne = new SingerOne();
        singerOne.setFirstName(null);
        singerOne.setLastName("Mayer");
        return singerOne;
    }
}