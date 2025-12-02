package com.example.demo.global.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // null 은 defaultValue 로 대체되니까 허용
        if (value == null) return true;
        return value >= 1;
    }
}
