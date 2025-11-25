package com.example.umc_spring_first.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCreateRequest { //리뷰 생성용 DTO -> 리뷰 생성 시 클라이언트가 보내는 데이터를 받는 DTO

    // 어떤 가게에 대한 리뷰인지
    private Long storeId;

    // 별점
    private Float rating;

    // 리뷰 내용
    private String content;

    // 이미지 URL 같은 거 쓰려면 여기도 추가 가능
    // private String image;
}
