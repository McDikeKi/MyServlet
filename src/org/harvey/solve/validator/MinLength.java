package org.harvey.solve.validator;


import javax.validation.Constraint;
import javax.validation.Payload;

import org.harvey.solve.validator.impl.MinLengthValidatorImpl;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MinLengthValidatorImpl.class)
public @interface MinLength {
	int value();
	String message();
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        MinLength[] value();
    }
}
