package com.example.umc9th.domain.question.entity;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.question.enums.QuestionType;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "question")
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title; //문의 제목

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private QuestionType questionType = QuestionType.ETC; //문의 유형

    @Column(name = "content", nullable = false)
    private String content; //문의 내용

    @Column(name = "is_answered", nullable = false)
    private boolean answered; //답변 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
