package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            String email,
            String password,
            String phone,
            @ExistFoods
            List<Long> preferCategory
    ){}

    // 로그인
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}