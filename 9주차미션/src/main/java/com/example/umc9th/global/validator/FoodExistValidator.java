package com.example.umc9th.global.validator;

import com.example.umc9th.domain.user.repository.FoodRepository;
import com.example.umc9th.domain.user.exception.code.FoodErrorCode;
import com.example.umc9th.global.annotation.ExistFoods;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodExistValidator implements ConstraintValidator<ExistFoods, List<Long>> {

    private final FoodRepository foodRepository;

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {

        if (values == null || values.isEmpty()) return true; // 값 없으면 통과

        boolean isValid = values.stream()
                .allMatch(foodRepository::existsById);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(FoodErrorCode.FOOD_NOT_FOUND.getMessage())
                    .addConstraintViolation();
        }

        return isValid;
    }
}
