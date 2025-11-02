package com.example.demo.domain.mission;

import com.example.demo.domain.member.Users;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "permission_mission")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PermissionMission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private Users user;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="mission_id", nullable=false)
    private Mission mission;
}