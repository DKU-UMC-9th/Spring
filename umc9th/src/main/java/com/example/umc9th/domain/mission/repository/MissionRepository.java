package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.dto.HomeMissionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT COUNT(um) FROM UserMission um WHERE um.member.id = :memberId AND um.status = true")
    long countPerformedMissions(@Param("memberId") Long memberId);

    @Query("""
        SELECT new com.example.umc9th.domain.mission.dto.HomeMissionDto(
            :region,
            m.id,
            s.name,
            m.point
        )
        FROM Mission m
        JOIN m.store s
        WHERE s.address LIKE CONCAT(:region, '%')
          AND NOT EXISTS (
              SELECT 1 FROM UserMission um
              WHERE um.mission.id = m.id
                AND um.member.id = :memberId
          )
        ORDER BY m.id DESC
    """)
    Page<HomeMissionDto> findUnjoinedMissions(
            @Param("region") String region,
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}
