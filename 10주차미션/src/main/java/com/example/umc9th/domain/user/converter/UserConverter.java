package com.example.umc9th.domain.user.converter;

import com.example.umc9th.domain.user.dto.UserReqDTO.UserReqDTO;
import com.example.umc9th.domain.user.dto.UserResDTO.UserResDTO;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.global.auth.enums.Role;

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
        // address와 specAddress를 합쳐서 저장
        String fullAddress = dto.address();
        if (dto.specAddress() != null && !dto.specAddress().isEmpty()) {
            fullAddress = dto.address() + " " + dto.specAddress();
        }

        return User.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(fullAddress)
                .gender(dto.gender())
                .email(dto.email())
                .password(password)
                .role(role)
                .phoneNum(dto.phoneNum())
                .build();
    }
}