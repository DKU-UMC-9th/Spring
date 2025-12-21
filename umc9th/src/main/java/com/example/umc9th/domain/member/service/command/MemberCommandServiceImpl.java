package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.entity.Interest;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.MemberInterest;
import com.example.umc9th.domain.member.enums.Role;
import com.example.umc9th.domain.member.exception.InterestException;
import com.example.umc9th.domain.member.exception.code.InterestErrorCode;
import com.example.umc9th.domain.member.repository.InterestRepository;
import com.example.umc9th.domain.member.repository.MemberInterestRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final MemberInterestRepository memberInterestRepository;
    private final InterestRepository interestRepository;

    // Password Encoder
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    @Transactional
    public MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    ){
        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(dto.password());

        // 사용자 생성: 유저 / 관리자는 따로 API 만들어서 관리
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);
        //DB 적용
        memberRepository.save(member);

        //선호 음식 여부 존재 확인
        if(dto.preferCategory().size() > 0){
            List<MemberInterest> memberInterestList = new ArrayList<>();

            // 선호 음식 ID별 조회
            for (Long id : dto.preferCategory()){

                // 음식 존재 여부 검증
                Interest interest = interestRepository.findById(id)
                        .orElseThrow(() -> new InterestException(InterestErrorCode.NOT_FOUND));

                // MemberFood 엔티티 생성 (컨버터 사용해야 함)
                MemberInterest memberFood = MemberInterest.builder()
                        .member(member)
                        .interest(interest)
                        .build();

                // 사용자 - 음식 (선호 음식) 추가
                memberInterestList.add(memberFood);
            }

            // 모든 관심 음식 추가: DB 적용
            memberInterestRepository.saveAll(memberInterestList);


        }
        // 응답 DTO 생성
        return MemberConverter.toJoinDTO(member);

    }
}