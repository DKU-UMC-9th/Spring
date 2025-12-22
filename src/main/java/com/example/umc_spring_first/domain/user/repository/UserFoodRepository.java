package com.example.umc_spring_first.domain.user.repository;

import com.example.umc_spring_first.domain.user.entity.mapping.UserFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFoodRepository extends JpaRepository<UserFood, Long> {
}