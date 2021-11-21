import configuration.ConfigurationClassForAnnotation;
import configuration.ConfigurationClassForApplicationConversionServiceFactoryBean;
import configuration.ConfigurationClassForConverter;
import configuration.ConfigurationClassForValidator;
import entity.SingerOne;
import entity.SingerThree;
import entity.SingerTwo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import service.SingerValidationService;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Executor {

    public static void main(String[] args) {

        propertyEditorRegistrar();
        converter();
        applicationConversionServiceFactoryBean();
        validator();
        annotation();

    }

    private static void propertyEditorRegistrar() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load(new ClassPathResource("configuration/configuration.xml"));
        ctx.refresh();

        System.out.println("---DateTimeEditorRegistrar implements PropertyEditorRegistrar---");
        SingerOne eric = ctx.getBean("eric", SingerOne.class);
        System.out.println(eric);
        SingerOne countrySinger = ctx.getBean("countrySinger", SingerOne.class);
        System.out.println(countrySinger);

        ctx.close();
    }

    private static void converter() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationClassForConverter.class);

        System.out.println("\n---StringToDateTimeConverter implements Converter---");
        SingerOne john = ctx.getBean("john", SingerOne.class);
        System.out.println(john);

        System.out.println("\n---SingerToAnotherSingerConverter implements Converter---");
        ConversionService conversionService = ctx.getBean(ConversionService.class);
        SingerTwo singerTwo = conversionService.convert(john, SingerTwo.class);
        System.out.println(singerTwo);

        System.out.println("\n---Default Converters (springframework.core.convert.support)---");
        String[] strings = conversionService.convert("a,b,c", String[].class);
        Arrays.stream(strings).forEach(System.out::print);

        ctx.close();
    }

    private static void applicationConversionServiceFactoryBean() {
        System.out.println("\n\n---ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean---");

        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationClassForApplicationConversionServiceFactoryBean.class);
        SingerOne john = ctx.getBean("john", SingerOne.class);
        System.out.println(john);

        ConversionService conversionService = ctx.getBean(ConversionService.class);
        String birthDate = conversionService.convert(john.getBirthDate(), String.class);
        System.out.println(birthDate);

        ctx.close();
    }

    private static void validator() {
        System.out.println("\n---SingerValidator implements Validator---");

        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationClassForValidator.class);

        SingerOne john = ctx.getBean("john", SingerOne.class);
        Validator singerValidator = ctx.getBean("singerValidator", Validator.class);

        BeanPropertyBindingResult result = new BeanPropertyBindingResult(john, "John");

        ValidationUtils.invokeValidator(singerValidator, john, result);

        List<ObjectError> errors = result.getAllErrors();
        errors.forEach(System.out::print);

        ctx.close();
    }

    private static void annotation() {
        System.out.println("\n\n---SingerThree with annotations---");

        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationClassForAnnotation.class);

        SingerValidationService singerValidationService = ctx.getBean("singerValidationService", SingerValidationService.class);

        SingerThree mayer = ctx.getBean("mayer", SingerThree.class);
        validateSingerThree(mayer, singerValidationService);

        SingerThree countrySinger = ctx.getBean("countrySinger", SingerThree.class);
        validateSingerThree(countrySinger, singerValidationService);

        ctx.close();
    }

    private static void validateSingerThree(SingerThree singerThree, SingerValidationService singerValidationService) {
        Set<ConstraintViolation<SingerThree>> violations = singerValidationService.validateSingerAnnotation(singerThree);
        violations.forEach(v -> System.out.println("Singer: " + singerThree.getFirstName() + " / " + v.getPropertyPath() + " / " + v.getInvalidValue() + " / " + v.getMessage()));
    }
}