package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("""
			select m
		  from MemberMission mm
		  join mm.mission m
		  where mm.member.id = :memberId
		    and mm.complete = :complete
		    and (:lastId is null or m.id < :lastId)
		  order by m.id desc
		""")
    Slice<Mission> findMissions(
            @Param("memberId") Long memberId,
            @Param("complete") boolean complete, //false(진행중), true(진행 완료)
            @Param("lastId") Long lastId,        //첫 페이지면 null
            Pageable pageable
    );

    @EntityGraph(attributePaths = "restaurant")
    @Query("""
			select m
			from Mission m
			join m.restaurant rs 
			join rs.region rg
			where rg.name = :regionName
				and m.expiredAt >= :today
				and (:lastId is null or m.id < :lastId)
			order by m.id desc
		""")
    Slice<Mission> findMissionsByRegion(
            @Param("regionName") String regionName,
            @Param("today") LocalDate today,
            @Param("lastId") Long lastId,      // 첫 페이지면 null
            Pageable pageable
    );

	Page<Mission> findAllByRestaurant(Restaurant restaurant, PageRequest pageRequest);
}
