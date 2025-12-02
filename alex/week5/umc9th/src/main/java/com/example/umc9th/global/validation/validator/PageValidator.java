package com.example.umc9th.global.validation.validator;

import com.example.umc9th.global.validation.annotation.ValidPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        return page != null && page >= 1;
    }
}
