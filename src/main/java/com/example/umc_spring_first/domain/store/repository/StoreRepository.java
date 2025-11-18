package com.example.umc_spring_first.domain.store.repository;

import com.example.umc_spring_first.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
