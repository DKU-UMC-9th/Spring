// UserPreferRepository.java
package com.example.umc_spring_first.domain.prefer.repository;

import com.example.umc_spring_first.domain.prefer.entity.UserPrefer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferRepository extends JpaRepository<UserPrefer, Long> {
    boolean existsByUserIdAndPrefer_Id(Long userId, Long preferId);
}
