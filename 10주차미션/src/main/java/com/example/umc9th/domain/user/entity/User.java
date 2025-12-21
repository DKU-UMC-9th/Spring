package com.example.umc9th.domain.user.entity;

import com.example.umc9th.domain.user.entity.mapping.UserFood;
import com.example.umc9th.domain.user.enums.Gender;
import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.global.auth.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    private Gender gender = Gender.NONE;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column
    private LocalDate birth;

    @Column
    private String address;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 20)
    private String phoneNum;

    @Builder.Default
    @Column(nullable = false)
    private Integer point = 0;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserFood> userFoodList = new ArrayList<>();
}
