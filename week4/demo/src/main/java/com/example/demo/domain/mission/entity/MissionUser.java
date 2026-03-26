package com.example.demo.domain.mission.entity;

import com.example.demo.global.entity.BaseEntity;
import com.example.demo.domain.member.entity.Users;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "mission_user")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MissionUser extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="mission_id", nullable=false)
    private Mission mission;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private Users user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionStatus missionStatus;

    @Column(nullable=false, length=200)
    private String content;
}
