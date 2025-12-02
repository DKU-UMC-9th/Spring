package com.example.week4.repository;

import com.example.week4.domain.User;
import com.example.week4.domain.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, UserMission.UserMissionId> {

    @Query("SELECT um FROM UserMission um JOIN FETCH um.mission m JOIN FETCH m.store WHERE um.user = :user AND um.is_complete = :isComplete")
    Page<UserMission> findAllByUserAndIsComplete(@Param("user") User user, @Param("isComplete") Boolean isComplete, Pageable pageable);

    @Query("SELECT um FROM UserMission um WHERE um.user.id = :userId AND um.mission.id = :missionId")
    Optional<UserMission> findByUserIdAndMissionId(@Param("userId") Long userId, @Param("missionId") Long missionId);
}
