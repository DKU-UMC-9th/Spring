package com.example.umc_spring_first.global.apiPayload.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // null 이면 실패하게 할지, 통과시킬지는 취향인데
        // 과제 조건상 page는 무조건 1 이상이어야 하니까 null도 실패 처리하자
        if (value == null) {
            return false;
        }
        return value >= 1;
    }
}
