package configuration;

import converters.SingerToAnotherSingerConverter;
import converters.StringToDateTimeConverter;
import entity.SingerOne;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.converter.Converter;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@PropertySource("configuration/config.properties")
@Configuration
public class ConfigurationClassForConverter {

    @Value("${date.format.pattern}")
    private String dateFormatPattern;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public SingerOne john(@Value("${countrySinger.firstName}") String firstName,
                          @Value("${countrySinger.lastName}") String lastName,
                          @Value("${countrySinger.personalSite}") URL personalSite,
                          @Value("${countrySinger.birthDate}") DateTime birthDate) {
        SingerOne singerOne = new SingerOne();
        singerOne.setFirstName(firstName);
        singerOne.setLastName(lastName);
        singerOne.setPersonalSite(personalSite);
        singerOne.setBirthDate(birthDate);
        return singerOne;
    }

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        Set<Converter> converters = new HashSet<>();
        converters.add(converter());
        converters.add(singerConverter());

        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(converters);
        conversionServiceFactoryBean.afterPropertiesSet();

        return conversionServiceFactoryBean;
    }

    @Bean
    public StringToDateTimeConverter converter() {
        StringToDateTimeConverter converter = new StringToDateTimeConverter();
        converter.setDatePattern(dateFormatPattern);
        converter.init();
        return converter;
    }

    @Bean
    public SingerToAnotherSingerConverter singerConverter() {
        return new SingerToAnotherSingerConverter();
    }
}