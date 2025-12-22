package com.example.umc_spring_first.domain.user.converter;

import com.example.umc_spring_first.domain.user.dto.req.UserReqDTO;
import com.example.umc_spring_first.domain.user.dto.res.UserResDTO;
import com.example.umc_spring_first.domain.user.entity.User;
import com.example.umc_spring_first.global.auth.enums.Role;

public class UserConverter {

    // Entity -> DTO
    public static UserResDTO.JoinDTO toJoinDTO(User user) {
        return UserResDTO.JoinDTO.builder()
                .userId(user.getId())
                .createAt(user.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static User toUser(UserReqDTO.JoinDTO dto, String password, Role role) {


        return User.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .email(dto.email())
                .password(password)
                .role(role)
                .phone(dto.phone())
                .build();
    }
}