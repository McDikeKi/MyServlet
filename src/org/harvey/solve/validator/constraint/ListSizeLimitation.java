package org.harvey.solve.validator.constraint;

import java.lang.annotation.Repeatable;
import javax.validation.Constraint;
import javax.validation.Payload;

import org.harvey.solve.validator.ListSizeLimitationValidator;

@Constraint(validatedBy = ListSizeLimitationValidator.class)
@Repeatable(ListSizeLimitations.class)
public @interface ListSizeLimitation {
    String message();
    String listFieldName();
    String limitedFieldName();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}