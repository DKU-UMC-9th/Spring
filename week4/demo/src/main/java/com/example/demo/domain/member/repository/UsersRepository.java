package com.example.demo.domain.member.repository;

import com.example.demo.domain.member.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findById(Long userId);

    @Query("select u from Users u WHERE u.id=:userId")
    Optional<Users> findById2(Long userId);
}
