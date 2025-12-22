package com.example.umc_spring_first.domain.mission.repository;

import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import com.example.umc_spring_first.domain.mission.entity.QMission;
import com.example.umc_spring_first.domain.mission.entity.QUserMission;
import com.example.umc_spring_first.domain.mission.enums.UserMissionStatus;
import com.example.umc_spring_first.domain.store.entity.QStore;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionQueryDsl {

    private final JPAQueryFactory query;

    // 2번: 특정 가게의 미션 목록
    @Override
    public Page<MissionResDTO.MissionPreviewDTO> searchStoreMissions(
            Long storeId,
            Pageable pageable
    ) {
        QMission m = QMission.mission;
        QStore s = QStore.store;

        List<MissionResDTO.MissionPreviewDTO> content = query
                .select(
                        com.querydsl.core.types.Projections.constructor(
                                MissionResDTO.MissionPreviewDTO.class,
                                m.id,
                                s.name,
                                m.point,
                                m.description
                        )
                )
                .from(m)
                .join(m.store, s)
                .where(m.store.id.eq(storeId))
                .orderBy(m.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = query
                .select(m.count())
                .from(m)
                .where(m.store.id.eq(storeId))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    // 3번: 내가 진행중인 미션 목록
    @Override
    public Page<MissionResDTO.MissionPreviewDTO> searchMyInProgressMissions(
            Long userId,
            Pageable pageable
    ) {
        QUserMission um = QUserMission.userMission;
        QMission m = QMission.mission;
        QStore s = QStore.store;

        List<MissionResDTO.MissionPreviewDTO> content = query
                .select(
                        com.querydsl.core.types.Projections.constructor(
                                MissionResDTO.MissionPreviewDTO.class,
                                m.id,
                                s.name,
                                m.point,
                                m.description
                        )
                )
                .from(um)
                .join(um.mission, m)
                .join(m.store, s)
                .where(
                        um.user.id.eq(userId)
                                .and(um.status.eq(UserMissionStatus.IN_PROGRESS))
                )
                .orderBy(um.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = query
                .select(um.count())
                .from(um)
                .where(
                        um.user.id.eq(userId)
                                .and(um.status.eq(UserMissionStatus.IN_PROGRESS))
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
