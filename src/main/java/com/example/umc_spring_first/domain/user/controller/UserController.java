package com.example.umc_spring_first.domain.user.controller;

import com.example.umc_spring_first.domain.user.dto.req.UserReqDTO;
import com.example.umc_spring_first.domain.user.dto.res.UserResDTO;
import com.example.umc_spring_first.domain.user.exception.code.UserSuccessCode;
import com.example.umc_spring_first.domain.user.service.command.UserCommandService;
import com.example.umc_spring_first.domain.user.service.query.UserQueryService;
import com.example.umc_spring_first.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService UserCommandService;
    private final UserQueryService UserQueryService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<UserResDTO.JoinDTO> signUp(
            @RequestBody @Valid UserReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, UserCommandService.signup(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<UserResDTO.LoginDTO> login(
            @RequestBody @Valid UserReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, UserQueryService.login(dto));
    }
}