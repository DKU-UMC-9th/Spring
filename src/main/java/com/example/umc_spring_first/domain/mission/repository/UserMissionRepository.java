package com.example.umc_spring_first.domain.mission.repository;

import com.example.umc_spring_first.domain.mission.dto.HomeMissionRowDto;
import com.example.umc_spring_first.domain.mission.dto.MyMissionRowDto;
import com.example.umc_spring_first.domain.mission.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 3번: 내가 진행/완료한 미션 목록 (정렬: m.id DESC)
    @Query("""
        select new com.example.umc_spring_first.domain.mission.dto.MyMissionRowDto(
            m.point, s.name, m.description, um.status
        )
        from UserMission um
        join um.mission m
        join m.store s
        where um.user.id = :userId
          and (:status is null or um.status = :status)
        order by m.id desc
        """)
    Page<MyMissionRowDto> findMyMissions(
            @Param("userId") Long userId,
            @Param("status") String status,   // 'y' / 'n' 등
            Pageable pageable
    );

    // 4번: 홈 화면 - 진행 가능('n') 미션 목록 (정렬: deadline ASC)
    @Query("""
        select new com.example.umc_spring_first.domain.mission.dto.HomeMissionRowDto(
            m.point, m.description, um.deadline, s.name, s.address
        )
        from UserMission um
        join um.mission m
        join m.store s
        where um.user.id = :userId
          and um.status = :status
        order by um.deadline asc
        """)
    Page<HomeMissionRowDto> findHomeMissions(
            @Param("userId") Long userId,
            @Param("status") String status,   // 예: 'n'
            Pageable pageable                 // PageRequest.of(page, size)
    );
}