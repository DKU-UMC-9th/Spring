package com.example.umc_spring_first.domain.mission.repository;

import com.example.umc_spring_first.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
