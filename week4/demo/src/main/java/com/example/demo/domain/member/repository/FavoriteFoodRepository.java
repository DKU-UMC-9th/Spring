package com.example.demo.domain.member.repository;

import com.example.demo.domain.member.entity.FavoriteFood;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FavoriteFoodRepository extends JpaRepository<FavoriteFood,Long> {

}
