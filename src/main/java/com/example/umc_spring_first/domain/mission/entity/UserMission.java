package com.example.umc_spring_first.domain.mission.entity;

import com.example.umc_spring_first.domain.mission.enums.UserMissionStatus;
import com.example.umc_spring_first.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_mission")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    private LocalDateTime deadline;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @Enumerated(EnumType.STRING)   // enum이라 추가
    private UserMissionStatus status;  // enum으로 변경
}
