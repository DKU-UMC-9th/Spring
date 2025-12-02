// src/main/java/com/example/umc_spring_first/domain/mission/repository/UserMissionRepository.java
package com.example.umc_spring_first.domain.mission.repository;

import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import com.example.umc_spring_first.domain.mission.entity.UserMission;
import com.example.umc_spring_first.domain.mission.enums.UserMissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 3번: 내가 진행중인 미션 목록 (um.id DESC 정렬)
    @Query("""
        select new com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO$MyMissionPreviewDTO(
            um.id,
            m.point,
            s.name,
            m.description,
            um.status,
            um.deadline
        )
        from UserMission um
        join um.mission m
        join m.store s
        where um.user.id = :userId
          and um.status = :status
        order by um.id desc
        """)
    Page<MissionResDTO.MyMissionPreviewDTO> findMyMissions(
            @Param("userId") Long userId,
            @Param("status") UserMissionStatus status,
            Pageable pageable
    );
}
