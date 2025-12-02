package com.example.demo.global.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PageValidator.class)
public @interface ValidPage {

    String message() default "page must be greater than or equal to 1";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
