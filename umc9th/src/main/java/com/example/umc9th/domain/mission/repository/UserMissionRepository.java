package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    List<UserMission> findByMemberIdAndStatusInOrderByDeadlineDesc(Long memberId, List<Boolean> statuses);

    boolean existsByMemberAndMission(Member member, Mission mission);

    List<UserMission> findByMemberId(Long memberId);

    // 미션 성공 처리 시: 해당 회원의 해당 user_mission만 조회
    Optional<UserMission> findByIdAndMemberId(Long id, Long memberId);
}
