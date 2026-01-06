package com.example.umc9th.auth.cotroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class KakaoAuthController {

    private final KakaoAuthService kakaoAuthService;

    @PostMapping("/kakao")
    public JwtResponse kakaoLogin(
            @RequestHeader("Authorization") String authorization
    ) {
        String kakaoAccessToken = authorization.replace("Bearer ", "");
        return kakaoAuthService.login(kakaoAccessToken);
    }
}