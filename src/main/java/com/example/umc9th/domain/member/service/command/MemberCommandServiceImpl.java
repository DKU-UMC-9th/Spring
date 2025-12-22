package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Food;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.exception.FoodException;
import com.example.umc9th.domain.member.exception.code.FoodErrorCode;
import com.example.umc9th.domain.member.repository.FoodRepository;
import com.example.umc9th.domain.member.repository.MemberFoodRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.auth.enums.Role;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;

    // Password Encoder
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    @Transactional
    public MemberResDTO.JoinDTO signUp(MemberReqDTO.JoinDTO dto){

        // 솔트된 비밀번호 생성(=암호화된 비밀번호)
        String salt = passwordEncoder.encode(dto.password());

        // 사용자 생성
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);

        // DB 적용
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인
        if(!dto.preferCategory().isEmpty()){
            List<MemberFood> memberFoodList = new ArrayList<>();

            // 선호 음식 ID별 조회
            for (Long id : dto.preferCategory()){
                // 음식 존재 여부 검증
                Food food = foodRepository.findById(id)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

                // MemberFood 엔티티 생성 (converter 사용해야 함)
                MemberFood memberFood = MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build();

                // 사용자 - 음식 (선호 음식) 추가
                memberFoodList.add(memberFood);
            }
            // 모든 선호 음식 추가: DB 적용
            memberFoodRepository.saveAll(memberFoodList); //MemberFood 엔티티 객체들을 한 번에 DB에 INSERT
        }

        // 응답 DTO 생성
        return MemberConverter.toJoinDTO(member);
    }
}
