// UserPreferRepository.java
package com.example.umc_spring_first.domain.prefer.repository;

import com.example.umc_spring_first.domain.prefer.entity.UserPrefer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferRepository extends JpaRepository<UserPrefer, Long> {
    boolean existsByUserIdAndPreferId(Long userId, Long preferId); // 중복 방지용
}
