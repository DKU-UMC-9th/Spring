package com.example.loginmission.dto;

import lombok.Getter;
import lombok.Setter;

public class MemberRequestDTO {

    @Getter
    @Setter
    public static class JoinDto {
        private String email;
        private String password;
        private String name;
    }

    @Getter
    @Setter
    public static class LoginDto {
        private String email;
        private String password;
    }
}
