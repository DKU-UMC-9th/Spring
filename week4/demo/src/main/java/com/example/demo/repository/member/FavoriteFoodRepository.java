package com.example.demo.repository.member;

import com.example.demo.domain.member.FavoriteFood;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FavoriteFoodRepository extends JpaRepository<FavoriteFood,Long> {

}
