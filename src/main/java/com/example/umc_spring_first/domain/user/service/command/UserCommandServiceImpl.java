package com.example.umc_spring_first.domain.user.service.command;

import com.example.umc_spring_first.domain.user.converter.UserConverter;
import com.example.umc_spring_first.domain.user.dto.req.UserReqDTO;
import com.example.umc_spring_first.domain.user.dto.res.UserResDTO;
import com.example.umc_spring_first.domain.user.entity.Food;
import com.example.umc_spring_first.domain.user.entity.User;
import com.example.umc_spring_first.domain.user.entity.mapping.UserFood;
import com.example.umc_spring_first.domain.user.exception.FoodException;
import com.example.umc_spring_first.domain.user.exception.code.FoodErrorCode;
import com.example.umc_spring_first.domain.user.repository.FoodRepository;
import com.example.umc_spring_first.domain.user.repository.UserFoodRepository;
import com.example.umc_spring_first.domain.user.repository.UserRepository;
import com.example.umc_spring_first.global.auth.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final UserFoodRepository userFoodRepository;
    private final FoodRepository foodRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResDTO.JoinDTO signup(UserReqDTO.JoinDTO dto) {

        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(dto.password());

        // 사용자 생성
        User user = UserConverter.toUser(dto, salt, Role.ROLE_USER);

        // DB 저장
        userRepository.save(user);

        // 선호 음식 존재 여부 확인
        if (dto.preferCategory() != null && dto.preferCategory().size() > 0) {

            List<UserFood> userFoodList = new ArrayList<>();

            // 선호 음식 ID 별 조회
            for (Long id : dto.preferCategory()) {
                // 음식 존재 여부 검증
                Food food = foodRepository.findById(id)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.FOOD_NOT_FOUND));

                // UserFood 엔티티 생성
                UserFood userFood = UserFood.builder()
                        .user(user)
                        .food(food)
                        .build();

                userFoodList.add(userFood);
            }

            // DB 저장
            userFoodRepository.saveAll(userFoodList);
        }

        // to 응답 DTO
        return UserConverter.toJoinDTO(user);
    }
}