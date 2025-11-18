package com.example.umc_spring_first.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//BaseTimeEntity는 모든 엔티티에 공통으로 들어가는 생성시간/수정시간 필드를 모아 둔 추상 상위 클래스.
//각 엔티티에서 extends BaseTimeEntity만 해주면 createdAt / updatedAt을 자동으로 관리.

@Getter
@MappedSuperclass                           // 상속 엔티티에 매핑 정보 전달
@EntityListeners(AuditingEntityListener.class)  // Auditing 이벤트 수신
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
