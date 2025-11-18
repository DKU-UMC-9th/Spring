package com.example.umc_spring_first.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -2040355534L;

    public static final QUser user = new QUser("user");

    public final StringPath address = createString("address");

    public final StringPath birthday = createString("birthday");

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Integer> gender = createNumber("gender", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.example.umc_spring_first.domain.inquiry.entity.Inquiry, com.example.umc_spring_first.domain.inquiry.entity.QInquiry> inquiries = this.<com.example.umc_spring_first.domain.inquiry.entity.Inquiry, com.example.umc_spring_first.domain.inquiry.entity.QInquiry>createList("inquiries", com.example.umc_spring_first.domain.inquiry.entity.Inquiry.class, com.example.umc_spring_first.domain.inquiry.entity.QInquiry.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final ListPath<com.example.umc_spring_first.domain.notice.entity.Notice, com.example.umc_spring_first.domain.notice.entity.QNotice> notices = this.<com.example.umc_spring_first.domain.notice.entity.Notice, com.example.umc_spring_first.domain.notice.entity.QNotice>createList("notices", com.example.umc_spring_first.domain.notice.entity.Notice.class, com.example.umc_spring_first.domain.notice.entity.QNotice.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final ListPath<com.example.umc_spring_first.domain.review.entity.Review, com.example.umc_spring_first.domain.review.entity.QReview> reviews = this.<com.example.umc_spring_first.domain.review.entity.Review, com.example.umc_spring_first.domain.review.entity.QReview>createList("reviews", com.example.umc_spring_first.domain.review.entity.Review.class, com.example.umc_spring_first.domain.review.entity.QReview.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> updateAt = createDateTime("updateAt", java.time.LocalDateTime.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

