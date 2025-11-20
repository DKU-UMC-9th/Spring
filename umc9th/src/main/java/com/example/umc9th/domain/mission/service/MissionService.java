package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    /**
     * 미션 도전하기
     */
    @Transactional
    public MissionResDTO.ChallengeDTO challengeMission(Long memberId, Long missionId, LocalDate deadline) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        // 이미 도전 중인지 체크
        if (userMissionRepository.existsByMemberAndMission(member, mission)) {
            throw new MissionException(MissionErrorCode.ALREADY_CHALLENGING);
        }

        // 마감 기한 기본값 설정 (null이면 +7일)
        LocalDate finalDeadline = (deadline != null) ? deadline : LocalDate.now().plusDays(7);

        // UserMission 생성 및 저장
        UserMission userMission = MissionConverter.toUserMission(member, mission, finalDeadline);
        userMissionRepository.save(userMission);

        return MissionConverter.toChallengeDTO(userMission);
    }

    /**
     * 내 미션 목록 조회 (진행중 + 성공)
     */
    @Transactional
    public List<MissionResDTO.MyMissionDTO> getMyMissions(Long memberId) {

        // 유저 유효성 검사
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        List<UserMission> list = userMissionRepository.findByMemberId(memberId);

        return list.stream()
                .map(MissionConverter::toMyMissionDTO)
                .toList();
    }

    /**
     * 미션 성공 처리
     */
    @Transactional
    public MissionResDTO.SuccessDTO successMission(Long memberId, Long userMissionId) {

        // 해당 회원의 user_mission인지 검증
        UserMission userMission = userMissionRepository.findByIdAndMemberId(userMissionId, memberId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.USER_MISSION_NOT_FOUND));

        // 이미 성공 상태면 그냥 둠 (idempotent)
        if (!userMission.isStatus()) {
            // 이미 SUCCESS 상태 → 그대로 응답
            return MissionConverter.toSuccessDTO(userMission);
        }

        // 진행중 → 성공 처리
        userMission.complete();  // status = false

        return MissionConverter.toSuccessDTO(userMission);
    }
}
