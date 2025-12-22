package com.example.umc_spring_first.domain.mission.repository;

import com.example.umc_spring_first.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionQueryDsl {

    // 2번: 특정 가게의 미션 목록 (엔티티 기반) / 구현체인 impl이 뭘 구현해야하는지 명시해줌.
    Page<Mission> searchStoreMissions(
            Long storeId,
            Pageable pageable
    );
}
