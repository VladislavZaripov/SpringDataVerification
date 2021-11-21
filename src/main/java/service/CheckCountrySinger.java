package service;

import validators.CountrySingerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = CountrySingerValidator.class)
@Documented
public @interface CheckCountrySinger {
    String message() default "---@CheckCountrySinger--- Country Singer should have gender and last name defined";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}