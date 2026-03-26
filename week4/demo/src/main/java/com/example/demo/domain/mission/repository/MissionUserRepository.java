package com.example.demo.domain.mission.repository;

import com.example.demo.domain.member.entity.Users;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.MissionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MissionUserRepository extends JpaRepository<MissionUser,Long> {
    List<MissionUser> findByUser_IdOrderByUpdatedAtDesc(int userId);
    @Query("select mu from MissionUser mu join fetch mu.mission where mu.user.id = :userId order by mu.updatedAt desc ")
    List<MissionUser> findByUser_IdOrderByUpdatedAtDesc2(int userId);
    Optional<MissionUser> findByMissionAndUser(Mission mission, Users user);
    boolean existsByMissionAndUser(Mission mission, Users user);
}
