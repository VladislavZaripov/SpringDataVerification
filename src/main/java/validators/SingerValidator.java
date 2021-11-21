package validators;

import entity.SingerOne;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component("singerValidator")
public class SingerValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return SingerOne.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty");
    }
}