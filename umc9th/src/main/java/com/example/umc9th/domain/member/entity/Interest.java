package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.FoodName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "interest")
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodName name;

//    //연관 관계
//    @OneToMany(mappedBy = "interest")
//    private List<MemberInterest> memberInterests = new ArrayList<>();


}
