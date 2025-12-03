package com.example.umc_spring_first.domain.mission.repository;

import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import com.example.umc_spring_first.domain.mission.entity.QMission;
import com.example.umc_spring_first.domain.mission.entity.QUserMission;
import com.example.umc_spring_first.domain.mission.enums.UserMissionStatus;
import com.example.umc_spring_first.domain.store.entity.QStore;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserMissionRepositoryImpl implements UserMissionQueryDsl {

    private final JPAQueryFactory query;

    @Override
    public Page<MissionResDTO.MissionPreviewDTO> searchMyMissions(
            Long userId,
            UserMissionStatus status,
            Pageable pageable
    ) {
        QUserMission um = QUserMission.userMission;
        QMission m = QMission.mission;
        QStore s = QStore.store;

        List<MissionResDTO.MissionPreviewDTO> content = query
                .select(Projections.constructor(
                        MissionResDTO.MissionPreviewDTO.class,
                        um.id,
                        m.point,
                        s.name,
                        m.description,
                        um.status,
                        um.deadline
                ))
                .from(um)
                .join(um.mission, m)
                .join(m.store, s)
                .where(
                        um.user.id.eq(userId),
                        um.status.eq(status)
                )
                .orderBy(um.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query
                .select(um.count())
                .from(um)
                .where(
                        um.user.id.eq(userId),
                        um.status.eq(status)
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}
