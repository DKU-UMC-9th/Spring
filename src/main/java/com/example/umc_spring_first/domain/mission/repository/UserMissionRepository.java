package com.example.umc_spring_first.domain.mission.repository;

import com.example.umc_spring_first.domain.mission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository
        extends JpaRepository<UserMission, Long>, UserMissionQueryDsl {

    // 여기에는 별도 @Query 메서드 두지 말기
}
