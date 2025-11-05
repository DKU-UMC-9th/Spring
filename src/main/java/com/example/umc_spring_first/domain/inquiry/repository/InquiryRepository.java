package com.example.umc_spring_first.domain.inquiry.repository;

import com.example.umc_spring_first.domain.inquiry.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
