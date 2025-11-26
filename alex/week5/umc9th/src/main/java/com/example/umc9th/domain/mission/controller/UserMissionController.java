package com.example.umc9th.domain.mission.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;

import com.example.umc9th.domain.mission.service.UserMissionService;
import com.example.umc9th.domain.mission.dto.UserMissionResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.domain.mission.dto.UserMissionRequestCreate;

import jakarta.validation.Valid; 

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/mission/user")
@RequiredArgsConstructor
public class UserMissionController {

    private final UserMissionService userMissionService;

    @PostMapping("/create")
    public ApiResponse<UserMissionResponse> Create(
        @Valid @RequestBody UserMissionRequestCreate request
    ) {
        UserMissionResponse resp = userMissionService.createUserMission(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATE, resp);
    }
}