package com.example.umc_spring_first.domain.mission.repository;

import com.example.umc_spring_first.domain.mission.entity.QUserMission;
import com.example.umc_spring_first.domain.mission.entity.UserMission;
import com.example.umc_spring_first.domain.mission.enums.UserMissionStatus;
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
    public Page<UserMission> searchMyMissions(
            Long userId,
            UserMissionStatus status,
            Pageable pageable
    ) {
        QUserMission um = QUserMission.userMission;

        // 1) content 생성 (엔티티 반환)
        List<UserMission> content = query //usermission entity 꺼내옴
                .select(um)
                .from(um)
                .where(
                        um.user.id.eq(userId),
                        um.status.eq(status)
                )
                .orderBy(um.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 2) total count
        Long total = query
                .select(um.count())
                .from(um)
                .where(
                        um.user.id.eq(userId),
                        um.status.eq(status)
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
        //QueryDSL을 사용할 때는 직접 페이징 쿼리를 실행하므로
        //스프링이 자동으로 Page 객체를 만들어주지 않는다. 그래서 우리가 직접 Page 객체를 만들어줘야 한다.
        //그 역할이 바로 PageImpl 이다.
    }
}
