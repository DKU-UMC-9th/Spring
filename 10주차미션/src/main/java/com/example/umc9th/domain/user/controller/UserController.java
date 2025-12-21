package com.example.umc9th.domain.user.controller;

import com.example.umc9th.domain.user.dto.UserReqDTO.UserReqDTO;
import com.example.umc9th.domain.user.dto.UserResDTO.UserResDTO;
import com.example.umc9th.domain.user.exception.code.UserSuccessCode;
import com.example.umc9th.domain.user.service.command.UserCommandService;
import com.example.umc9th.domain.user.service.query.UserQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService UserQueryService;

    @PostMapping("/sign-up")
    public ApiResponse<UserResDTO.JoinDTO> signUp(
            @RequestBody @Valid UserReqDTO.JoinDTO dto
    ){
        UserResDTO.JoinDTO response = userCommandService.signup(dto);
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, response);
    }

    @PostMapping("/login")
    public ApiResponse<UserResDTO.LoginDTO> login(
        @RequestBody @Valid UserReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, UserQueryService.login(dto));
    }
}

