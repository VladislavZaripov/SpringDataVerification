package service;

import entity.SingerThree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service("singerValidationService")
public class SingerValidationService {

    @Autowired
    private Validator validator;

    public Set<ConstraintViolation<SingerThree>> validateSingerAnnotation(SingerThree singerThree) {
        return validator.validate(singerThree);
    }
}