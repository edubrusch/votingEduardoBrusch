package com.eduardo.voting.validation.components;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CPFValidator.class)
public @interface CPFConstraint {
    String message() default "CPF must be valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
