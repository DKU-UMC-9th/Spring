// PreferRepository.java
package com.example.umc_spring_first.domain.prefer.repository;

import com.example.umc_spring_first.domain.prefer.entity.Prefer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferRepository extends JpaRepository<Prefer, Long> {
    boolean existsByMenu(String menu);
}
