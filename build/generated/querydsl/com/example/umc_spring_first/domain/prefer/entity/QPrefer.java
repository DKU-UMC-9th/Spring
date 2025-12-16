package com.example.umc_spring_first.domain.prefer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;


/**
 * QPrefer is a Querydsl query type for Prefer
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPrefer extends EntityPathBase<Prefer> {

    private static final long serialVersionUID = -1184394372L;

    public static final QPrefer prefer = new QPrefer("prefer");

    public final com.example.umc_spring_first.global.entity.QBaseTimeEntity _super = new com.example.umc_spring_first.global.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath menu = createString("menu");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPrefer(String variable) {
        super(Prefer.class, forVariable(variable));
    }

    public QPrefer(Path<? extends Prefer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrefer(PathMetadata metadata) {
        super(Prefer.class, metadata);
    }

}

