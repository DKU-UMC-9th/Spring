package com.example.loginmission.controller;

import com.example.loginmission.dto.MemberRequestDTO;
import com.example.loginmission.service.MemberService;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @Operation(summary = "Join Member", description = "Join a new member")
    public String signup(@RequestBody MemberRequestDTO.JoinDto request) {
        memberService.join(request);
        return "Signup Successful";
    }

    @PostMapping("/login")
    @Operation(summary = "Login Member", description = "Login and get JWT Token")
    public String login(@RequestBody MemberRequestDTO.LoginDto request) {
        return memberService.login(request);
    }
}
