package com.example.umc_spring_first.domain.store.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore is a Querydsl query type for Store
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = -379107614L;

    public static final QStore store = new QStore("store");

    public final StringPath address = createString("address");

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.example.umc_spring_first.domain.mission.entity.Mission, com.example.umc_spring_first.domain.mission.entity.QMission> missions = this.<com.example.umc_spring_first.domain.mission.entity.Mission, com.example.umc_spring_first.domain.mission.entity.QMission>createList("missions", com.example.umc_spring_first.domain.mission.entity.Mission.class, com.example.umc_spring_first.domain.mission.entity.QMission.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> ownerNumber = createNumber("ownerNumber", Integer.class);

    public final StringPath phone = createString("phone");

    public final ListPath<com.example.umc_spring_first.domain.review.entity.Review, com.example.umc_spring_first.domain.review.entity.QReview> reviews = this.<com.example.umc_spring_first.domain.review.entity.Review, com.example.umc_spring_first.domain.review.entity.QReview>createList("reviews", com.example.umc_spring_first.domain.review.entity.Review.class, com.example.umc_spring_first.domain.review.entity.QReview.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> updateAt = createDateTime("updateAt", java.time.LocalDateTime.class);

    public QStore(String variable) {
        super(Store.class, forVariable(variable));
    }

    public QStore(Path<? extends Store> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStore(PathMetadata metadata) {
        super(Store.class, metadata);
    }

}

