package com.example.umc_spring_first.domain.user.service.query;

import com.example.umc_spring_first.domain.user.dto.req.UserReqDTO;
import com.example.umc_spring_first.domain.user.dto.res.UserResDTO;
import com.example.umc_spring_first.domain.user.entity.User;
import com.example.umc_spring_first.domain.user.exception.UserException;
import com.example.umc_spring_first.domain.user.exception.code.UserErrorCode;
import com.example.umc_spring_first.domain.user.repository.UserRepository;
import com.example.umc_spring_first.global.auth.details.CustomUserDetails;
import com.example.umc_spring_first.global.auth.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResDTO.LoginDTO login(UserReqDTO.LoginDTO dto) {

        // user 조회
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new UserException(UserErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(user);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);


        // 응답 DTO 반환
        return UserResDTO.LoginDTO.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .build();
    }
}