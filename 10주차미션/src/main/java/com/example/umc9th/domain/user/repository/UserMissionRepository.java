package com.example.umc9th.domain.user.repository;

import com.example.umc9th.domain.user.entity.mapping.UserMission;
import com.example.umc9th.domain.user.enums.UserMissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    // 이미 도전 중인 미션인지 확인
    Optional<UserMission> findByUserIdAndMissionId(Long userId, Long missionId);
    Page<UserMission> findByUserIdAndStatus(Long userId, UserMissionStatus status, Pageable pageable);
}