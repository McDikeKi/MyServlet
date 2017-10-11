package org.harvey.solve.validator.impl;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.harvey.solve.validator.MinLength;
import org.springframework.stereotype.Service;

@SuppressWarnings("rawtypes")
@Service
public class MinLengthValidatorImpl implements ConstraintValidator<MinLength, List>{
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
