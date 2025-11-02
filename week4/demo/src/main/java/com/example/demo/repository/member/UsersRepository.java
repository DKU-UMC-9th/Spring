package com.example.demo.repository.member;

import com.example.demo.domain.member.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findById(Long userId);

    @Query("select u from Users u WHERE u.id=:userId")
    Optional<Users> findById2(Long userId);
}
