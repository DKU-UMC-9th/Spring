package com.example.umc_spring_first.domain.prefer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserPrefer is a Querydsl query type for UserPrefer
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPrefer extends EntityPathBase<UserPrefer> {

    private static final long serialVersionUID = 2098303271L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserPrefer userPrefer = new QUserPrefer("userPrefer");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.umc_spring_first.domain.user.entity.QUser user;

    public QUserPrefer(String variable) {
        this(UserPrefer.class, forVariable(variable), INITS);
    }

    public QUserPrefer(Path<? extends UserPrefer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserPrefer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserPrefer(PathMetadata metadata, PathInits inits) {
        this(UserPrefer.class, metadata, inits);
    }

    public QUserPrefer(Class<? extends UserPrefer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.umc_spring_first.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

