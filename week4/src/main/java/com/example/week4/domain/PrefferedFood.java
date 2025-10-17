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
@IdClass(PrefferedFood.PrefferedFoodId.class)
public class PrefferedFood {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    // getters and setters

    public static class PrefferedFoodId implements Serializable {
        private Long user;
        private Long food;

        // equals and hashCode
    }
}
