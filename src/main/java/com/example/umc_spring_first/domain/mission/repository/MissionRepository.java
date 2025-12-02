// src/main/java/com/example/umc_spring_first/domain/mission/repository/MissionRepository.java
package com.example.umc_spring_first.domain.mission.repository;

import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import com.example.umc_spring_first.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 2번: 특정 가게의 미션 목록 (미션 id DESC 정렬)
    @Query("""
        select new com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO$StoreMissionPreviewDTO(
            m.id,
            m.point,
            m.description
        )
        from Mission m
        where m.store.id = :storeId
        order by m.id desc
        """)
    Page<MissionResDTO.StoreMissionPreviewDTO> findStoreMissions(
            @Param("storeId") Long storeId,
            Pageable pageable
    );
}
