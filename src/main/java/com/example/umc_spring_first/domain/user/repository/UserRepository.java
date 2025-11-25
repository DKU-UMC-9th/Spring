package com.example.umc_spring_first.domain.user.repository;

import com.example.umc_spring_first.domain.user.dto.UserProfileDto;
import com.example.umc_spring_first.domain.user.entity.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
           select new com.example.umc_spring_first.domain.user.dto.UserProfileDto(
               u.name, u.email, u.phone, u.point
           )
           from User u
           where u.id = :userId
           """)
    Optional<UserProfileDto> findProfileById(@Param("userId") Long userId);
}
