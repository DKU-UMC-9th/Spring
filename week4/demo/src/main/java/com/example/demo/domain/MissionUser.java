package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "mission_user")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MissionUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="mission_id", nullable=false)
    private Mission mission;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private Users user;

    @Column(nullable=false, length=200)
    private String content;
}
