package com.example.demo.domain.mission.entity;

import com.example.demo.global.entity.BaseEntity;
import com.example.demo.domain.member.entity.Users;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "permission_mission")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PermissionMission extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private Users user;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="mission_id", nullable=false)
    private Mission mission;
}