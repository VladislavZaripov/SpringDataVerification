package configuration;

import converters.ApplicationConversionServiceFactoryBean;
import entity.SingerOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.net.URL;
import java.util.Locale;

@PropertySource("configuration/config.properties")
@Configuration
@ComponentScan(basePackages = "converters")
public class ConfigurationClassForApplicationConversionServiceFactoryBean {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Autowired
    ApplicationConversionServiceFactoryBean conversionService;

    @Bean
    public SingerOne john() throws Exception {
        SingerOne singerOne = new SingerOne();
        singerOne.setFirstName("John");
        singerOne.setLastName("Mayer");
        singerOne.setPersonalSite(new URL("http://johnmayer.com/"));
        singerOne.setBirthDate(conversionService.getDateTimeFormatter().parse("1977-10-16", Locale.ENGLISH));
        return singerOne;
    }
}