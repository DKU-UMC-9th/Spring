package com.example.week4.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
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

    public static class UserMissionId implements Serializable {
        private Long mission;
        private Long user;

        // equals and hashCode
    }
}
