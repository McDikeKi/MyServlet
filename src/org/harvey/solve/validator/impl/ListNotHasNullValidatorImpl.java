package org.harvey.solve.validator.impl;


import org.harvey.solve.validator.ListNotHasNull;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@SuppressWarnings("rawtypes")
@Service
public class ListNotHasNullValidatorImpl implements ConstraintValidator<ListNotHasNull, List> {


    @Override
    public void initialize(ListNotHasNull constraintAnnotation) {
    }

    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        for (Object object : list) {
            if (object == null) {
                return false;
            }
        }
        return true;
    }

}