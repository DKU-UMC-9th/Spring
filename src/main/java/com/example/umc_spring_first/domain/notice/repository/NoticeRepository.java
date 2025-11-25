package com.example.umc_spring_first.domain.notice.repository;

import com.example.umc_spring_first.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
