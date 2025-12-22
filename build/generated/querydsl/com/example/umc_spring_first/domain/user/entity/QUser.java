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

    public final com.example.umc_spring_first.global.entity.QBaseTimeEntity _super = new com.example.umc_spring_first.global.entity.QBaseTimeEntity(this);

    public final StringPath address = createString("address");

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<com.example.umc_spring_first.domain.user.enums.Gender> gender = createEnum("gender", com.example.umc_spring_first.domain.user.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final EnumPath<com.example.umc_spring_first.global.auth.enums.Role> role = createEnum("role", com.example.umc_spring_first.global.auth.enums.Role.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<com.example.umc_spring_first.domain.user.entity.mapping.UserFood, com.example.umc_spring_first.domain.user.entity.mapping.QUserFood> userFoodList = this.<com.example.umc_spring_first.domain.user.entity.mapping.UserFood, com.example.umc_spring_first.domain.user.entity.mapping.QUserFood>createList("userFoodList", com.example.umc_spring_first.domain.user.entity.mapping.UserFood.class, com.example.umc_spring_first.domain.user.entity.mapping.QUserFood.class, PathInits.DIRECT2);

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

