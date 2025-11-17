package com.example.demo.domain.mission.repository;

import com.example.demo.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission,Long> {
    List<Mission> findMissionsByContentOrderByUpdatedAt(String Content);
    @Query("select m from Mission m where m.content=:Content order by m.updatedAt desc")
    List<Mission> findMissionsByContentOrderByUpdatedAt2(String Content);
}
