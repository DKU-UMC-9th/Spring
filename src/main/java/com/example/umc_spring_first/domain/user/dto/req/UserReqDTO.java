package com.example.umc_spring_first.domain.user.dto.req;

import com.example.umc_spring_first.domain.user.enums.Gender;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {
    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            String specAddress,
            String email,
            String password,
            String phone,
            List<Long> preferCategory
    ) {}

    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}