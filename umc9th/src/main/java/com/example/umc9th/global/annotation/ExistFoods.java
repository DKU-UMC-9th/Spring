package com.example.umc9th.global.annotation;

import com.example.umc9th.global.validator.FoodExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

//<aside>
//🌟 **@Documented** - ****이 어노테이션은 사용자 정의 어노테이션을 만들 때 붙입니다.
//
//**@Target** - ****이 어노테이션은 어노테이션의 적용 범위를 지정합니다. 각 파라미터의 자세한 역할은 구글링을 해서 찾아보세용.
//
//**@Retention** - ****이 어노테이션은 어노테이션의 생명 주기를 지정합니다. 위의 코드는 RUNTIME이기에 실행 하는 동안에만 유효하게 됩니다.
//
//</aside>
@Documented
@Constraint(validatedBy = FoodExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistFoods {
    //여기서 디폴트 메시지를 설정합니다.
    String message() default "해당 음식이 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}