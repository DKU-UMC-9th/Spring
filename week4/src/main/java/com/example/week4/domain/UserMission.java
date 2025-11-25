package com.example.week4.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@lombok.Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@IdClass(UserMission.UserMissionId.class)
public class UserMission {

    private Boolean is_complete;

    @Id
    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public static class UserMissionId implements Serializable {
        private Long mission;
        private Long user;

        // equals and hashCode
    }
}
