package com.example.umc9th.auth.service;

import com.example.umc9th.auth.kakao.KakaoApiClient;
import com.example.umc9th.auth.kakao.dto.KakaoUserInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoAuthService {

    private final KakaoApiClient kakaoApiClient;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider; // ✅ 기존 것 그대로 사용

    public JwtResponse login(String kakaoAccessToken) {

        KakaoUserInfo kakaoUser = kakaoApiClient.getUserInfo(kakaoAccessToken);

        String providerId = kakaoUser.getId().toString();
        String email = kakaoUser.getKakaoAccount() != null
                ? kakaoUser.getKakaoAccount().getEmail()
                : null;

        User user = userRepository
                .findByProviderAndProviderId(Provider.KAKAO, providerId)
                .orElseGet(() ->
                        userRepository.save(
                                User.createKakaoUser(email, providerId)
                        )
                );

        String jwt = jwtProvider.createAccessToken(user);

        return new JwtResponse(jwt);
    }
}