package com.example.umc9th.domain.user.repository;

import com.example.umc9th.domain.user.entity.mapping.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    // 이미 도전 중인 미션인지 확인
    Optional<UserMission> findByUserIdAndMissionId(Long userId, Long missionId);
}