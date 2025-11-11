package com.example.umc9th.domain.alarm.entity;

import com.example.umc9th.domain.alarm.enums.AlarmType;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm")
public class Alarm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AlarmType alarmType; //알림 유형

    @Column(name = "title", nullable = false)
    private String title; //알림 제목

    @Column(name = "body", nullable = false)
    private String body; //알림 내용

    @Column(name = "is_confirmed", nullable = false)
    private boolean confirmed; //확인 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


}
