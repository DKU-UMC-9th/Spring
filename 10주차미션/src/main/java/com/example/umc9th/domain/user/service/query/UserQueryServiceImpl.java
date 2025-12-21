package com.example.umc9th.domain.user.service.query;

import com.example.umc9th.domain.user.dto.UserReqDTO.UserReqDTO;
import com.example.umc9th.domain.user.dto.UserResDTO.UserResDTO;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.exception.UserException;
import com.example.umc9th.domain.user.exception.code.UserErrorCode;
import com.example.umc9th.domain.user.repository.UserRepository;
import com.example.umc9th.global.auth.details.CustomUserDetails;
import com.example.umc9th.global.auth.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserResDTO.LoginDTO login(UserReqDTO.LoginDTO dto) {

        // 1️⃣ 이메일로 유저 조회
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        // 2️⃣ 비밀번호 검증
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new UserException(UserErrorCode.INVALID);
        }

        // 3️⃣ JWT 생성
        CustomUserDetails userDetails = new CustomUserDetails(user);
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // 4️⃣ 응답 DTO 반환
        return UserResDTO.LoginDTO.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .build();
    }
}
