package com.example.umc_spring_first.domain.mission.repository;

import com.example.umc_spring_first.domain.mission.entity.Mission;
import com.example.umc_spring_first.domain.mission.entity.QMission;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionQueryDsl {

    private final JPAQueryFactory query;

    @Override
    public Page<Mission> searchStoreMissions(Long storeId, Pageable pageable) {

        QMission m = QMission.mission;

        List<Mission> content = query
                .select(m)
                .from(m)
                .where(m.store.id.eq(storeId))
                .orderBy(m.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query
                .select(m.count())
                .from(m)
                .where(m.store.id.eq(storeId))
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
        //QueryDSL을 사용할 때는 직접 페이징 쿼리를 실행하므로
        //스프링이 자동으로 Page 객체를 만들어주지 않는다. 그래서 우리가 직접 Page 객체를 만들어줘야 한다.
        //그 역할이 바로 PageImpl 이다.
    }
}
