package com.example.umc9th.domain.agreement.entity;

import com.example.umc9th.global.common.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="agreement")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Agreement extends BaseTime {
  
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=1000)
    private String content;
}