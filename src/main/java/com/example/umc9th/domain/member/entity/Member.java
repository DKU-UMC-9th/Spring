package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.Address;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.SocialType;
import com.example.umc9th.domain.member.enums.Status;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 3, nullable = false)
    private String name;  //이름

    @Column(name = "nickname")
    private String nickname; //닉네임

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE; //성별

    @Column(name = "birth", nullable = false)
    private LocalDate birth; //생년월일

    @Column(name = "address", nullable = false)
    @Enumerated(EnumType.STRING)
    private Address address; //주소

    @Column(name = "detail_address", nullable = false)
    private String detailAddress; //상세주소

    @Column(name = "email")
    private String email; //이메일

    @Column(name = "phone_number")
    private String phoneNumber; //휴대폰번호

    @Column(name = "social_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private SocialType socialType = SocialType.LOCAL; //소셜로그인 타입

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0; //포인트

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE; //상태

    @Column(name = "inactive_date")
    private LocalDateTime inactiveDate; //비활성일

}
