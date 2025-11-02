package com.example.demo.domain.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name ="favorite_food")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteFood {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id",nullable = false)
    private Food food;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private Users user;
}
