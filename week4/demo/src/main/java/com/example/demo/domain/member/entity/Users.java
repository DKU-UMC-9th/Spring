package com.example.demo.domain.member.entity;

import com.example.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Users extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    private String email;

    @Column(nullable = false,length =100)
    private String password;

    @Column(nullable = false,length = 20)
    private String name;

    @Column(nullable = false)
    private Byte gender;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false,length = 50)
    private String address;

    @Column(nullable = false)
    private Integer point;
}
