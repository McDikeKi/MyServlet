package org.harvey.solve.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.harvey.solve.validator.constraint.MinLength;
import org.springframework.stereotype.Service;

@SuppressWarnings("rawtypes")
@Service
public class MinLengthValidator implements ConstraintValidator<MinLength, List>{
	private int value;

    @Override
    public void initialize(MinLength constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }
    
	@Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        if (list.size()<value) {
        	return false;
        }
        return true;
    }
}
