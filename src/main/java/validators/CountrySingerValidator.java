package validators;

import entity.SingerThree;
import service.CheckCountrySinger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountrySingerValidator implements ConstraintValidator<CheckCountrySinger, SingerThree> {

    @Override
    public boolean isValid(SingerThree singerThree, ConstraintValidatorContext constraintValidatorContext) {
        return singerThree.getGenre() == null ||
                (!singerThree.isCountrySinger() ||
                        (singerThree.getLastName() != null
                                && singerThree.getGender() != null));
    }

    @Override
    public void initialize(CheckCountrySinger constraintAnnotation) {
    }
}