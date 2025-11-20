package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    List<UserMission> findByMemberIdAndStatusInOrderByDeadlineDesc(Long memberId, List<Boolean> statuses);
    boolean existsByMemberAndMission(Member member, Mission mission);
}
