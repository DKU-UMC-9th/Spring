package com.example.demo.repository.mission;

import com.example.demo.domain.mission.MissionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MissionUserRepository extends JpaRepository<MissionUser,Long> {
    List<MissionUser> findByUser_IdOrderByUpdatedAtDesc(int userId);
    @Query("select mu from MissionUser mu join fetch mu.mission where mu.user.id = :userId order by mu.updatedAt desc ")
    List<MissionUser> findByUser_IdOrderByUpdatedAtDesc2(int userId);
}
