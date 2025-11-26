package com.example.umc9th.domain.user.repository;

import com.example.umc9th.domain.user.entity.mapping.UserFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// UserFoodRepository - UserFood 엔티티 관리
@Repository
public interface UserFoodRepository extends JpaRepository<UserFood, Long> {
}