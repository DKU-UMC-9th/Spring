package com.example.umc9th.domain.user.service.command;

import com.example.umc9th.domain.user.converter.UserConverter;
import com.example.umc9th.domain.user.dto.UserReqDTO.UserReqDTO;
import com.example.umc9th.domain.user.dto.UserResDTO.UserResDTO;
import com.example.umc9th.domain.user.entity.Food;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.entity.mapping.UserFood;
import com.example.umc9th.domain.user.exception.FoodException;
import com.example.umc9th.domain.user.exception.code.FoodErrorCode;
import com.example.umc9th.domain.user.repository.FoodRepository;
import com.example.umc9th.domain.user.repository.UserFoodRepository;
import com.example.umc9th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    @Override
    @Transactional
    public UserResDTO.JoinDTO signup(UserReqDTO.JoinDTO dto) {
        // 사용자 생성
        User user = UserConverter.toUser(dto);

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

        // 응답 DTO로 변환
        return UserConverter.toJoinDTO(user);
    }
}