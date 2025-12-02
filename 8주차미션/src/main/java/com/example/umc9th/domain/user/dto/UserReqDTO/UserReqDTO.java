package com.example.umc9th.domain.user.dto.UserReqDTO;

import com.example.umc9th.domain.user.enums.Gender;
import com.example.umc9th.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {
    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            String address,        // Address -> String으로 변경
            String specAddress,
            String email,
            String password,
            String phoneNum,
            @ExistFoods
            List<Long> preferCategory
    ) {}
}