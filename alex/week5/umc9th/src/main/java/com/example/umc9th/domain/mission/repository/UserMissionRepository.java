package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMissionRepository extends JpaRepository<UserMission,Long> {

    // 미션화면
    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함)
    @Query("""
        SELECT m
        FROM UserMission um 
        JOIN um.mission m
        WHERE um.user.id = :userId
          AND um.isComplete = :isComplete
          AND (:lastId IS NULL OR m.id < :lastId)
        ORDER BY m.id DESC
        """)
    Slice<Mission> findMissionsByIdAll(
            @Param("userId") Long userId,
            @Param("isComplete") Boolean isComplete,
            @Param("lastId") Long lastId,
            Pageable pageable
    );

    // 홈 화면 쿼리
    // (현재 선택 된 지역에서 도전이 가능한 미션 목록, 페이징 포함)
    @Query(value = """
        SELECT um
        FROM UserMission um
        JOIN FETCH um.mission m
        JOIN FETCH m.store s
        JOIN FETCH s.category  fc
        WHERE um.isComplete = false
          AND s.addr = :address
          AND um.user.id = :userId
        ORDER BY um.completeAt DESC
        """)
    Slice<UserMission> findMyMissionsAsEntity(
            @Param("userId") Long userId,
            @Param("address") String address,
            Pageable pageable
    );
}
