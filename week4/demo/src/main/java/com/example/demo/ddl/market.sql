use market;
CREATE TABLE users (
                       id        BIGINT NOT NULL AUTO_INCREMENT COMMENT '유저 고유 식별자',
                       email     VARCHAR(50) NOT NULL COMMENT '로그인 이메일(유니크)',
                       password  VARCHAR(100) NOT NULL COMMENT '비밀번호 해시(예: bcrypt 60자)',
                       name      VARCHAR(20) NOT NULL COMMENT '이름',
                       gender    TINYINT NOT NULL COMMENT '0:남, 1:여, 2:기타 등',
                       birth     DATE NOT NULL COMMENT '생년월일',
                       address   VARCHAR(50) NOT NULL COMMENT '주소',
                       point     INT NOT NULL DEFAULT 0 COMMENT '총 포인트',
                       PRIMARY KEY (id),
                       UNIQUE KEY UK_users_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='user_information';

-- 음식 카테고리
CREATE TABLE food (
                      id   BIGINT NOT NULL AUTO_INCREMENT COMMENT '음식 카테고리 ID',
                      name VARCHAR(40) NOT NULL COMMENT '음식 카테고리 이름',
                      PRIMARY KEY (id),
                      UNIQUE KEY UK_food_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='푸드카테고리';

-- 선호 음식 (유저-음식)
CREATE TABLE favorite_food (
                               id       BIGINT NOT NULL AUTO_INCREMENT COMMENT '유저선호음식코드',
                               food_id  BIGINT NOT NULL COMMENT '음식 카테고리 ID',
                               user_id  BIGINT NOT NULL COMMENT '유저 ID',
                               PRIMARY KEY (id),
                               UNIQUE KEY UK_favorite_food_user_food (user_id, food_id),
                               KEY IDX_favorite_food_food (food_id),
                               CONSTRAINT FK_favorite_food_food
                                   FOREIGN KEY (food_id) REFERENCES food (id)
                                       ON UPDATE CASCADE ON DELETE RESTRICT,
                               CONSTRAINT FK_favorite_food_users
                                   FOREIGN KEY (user_id) REFERENCES users (id)
                                       ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='선호음식';

-- 가게
CREATE TABLE foodmarket (
                            id       BIGINT NOT NULL AUTO_INCREMENT COMMENT '가게 id',
                            name     VARCHAR(40) NOT NULL COMMENT '상호명(유니크는 아님)',
                            address  VARCHAR(50) NOT NULL COMMENT '가게주소',
                            open     BOOLEAN NOT NULL COMMENT '영업중 여부',
                            content  VARCHAR(200) NOT NULL COMMENT '가게 소개',
                            PRIMARY KEY (id),
                            KEY IDX_foodmarket_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='푸드 마켓';

-- 미션(가게별)
CREATE TABLE mission (
                         id            BIGINT NOT NULL AUTO_INCREMENT COMMENT '고유 미션 ID',
                         market_id     BIGINT NOT NULL COMMENT '가게 id',
                         content       VARCHAR(100) NOT NULL COMMENT '미션 내용',
                         mission_point INT NOT NULL COMMENT '미션 포인트',
                         PRIMARY KEY (id),
                         KEY IDX_mission_market (market_id),
                         CONSTRAINT FK_mission_foodmarket
                             FOREIGN KEY (market_id) REFERENCES foodmarket (id)
                                 ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='마켓과 유저 사이의 미션';

-- 유저가 받은 미션(신규 신청 등)
CREATE TABLE mission_user (
                              id         BIGINT NOT NULL AUTO_INCREMENT,
                              mission_id BIGINT NOT NULL COMMENT '미션 ID',
                              user_id    BIGINT NOT NULL COMMENT '유저 ID',
                              content    VARCHAR(200) NOT NULL COMMENT '새 미션에 대한 내용',
                              PRIMARY KEY (id),
                              UNIQUE KEY UK_mission_user (mission_id, user_id),
                              KEY IDX_mission_user_mission (mission_id),
                              KEY IDX_mission_user_user (user_id),
                              CONSTRAINT FK_mission_user_mission
                                  FOREIGN KEY (mission_id) REFERENCES mission (id)
                                      ON UPDATE CASCADE ON DELETE CASCADE,
                              CONSTRAINT FK_mission_user_users
                                  FOREIGN KEY (user_id) REFERENCES users (id)
                                      ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='신규 미션 배정 내역';

-- 미션 권한/배정 토큰(사장님 확인용 번호)
CREATE TABLE permission_mission (
                                    id         BIGINT NOT NULL AUTO_INCREMENT COMMENT '유저 미션할당번호(제시용)',
                                    user_id    BIGINT NOT NULL COMMENT '유저 ID',
                                    mission_id BIGINT NOT NULL COMMENT '미션 ID',
                                    PRIMARY KEY (id),
                                    UNIQUE KEY UK_permission_mission (user_id, mission_id),
                                    KEY IDX_permission_mission_user (user_id),
                                    KEY IDX_permission_mission_mission (mission_id),
                                    CONSTRAINT FK_permission_mission_users
                                        FOREIGN KEY (user_id) REFERENCES users (id)
                                            ON UPDATE CASCADE ON DELETE CASCADE,
                                    CONSTRAINT FK_permission_mission_mission
                                        FOREIGN KEY (mission_id) REFERENCES mission (id)
                                            ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='미션 권한 배정';

-- 리뷰
CREATE TABLE review (
                        id        BIGINT NOT NULL AUTO_INCREMENT COMMENT '리뷰 ID',
                        user_id   BIGINT NOT NULL COMMENT '유저 ID',
                        market_id BIGINT NOT NULL COMMENT '가게 ID',
                        content   VARCHAR(100) NOT NULL COMMENT '리뷰 내용',
                        star      DECIMAL(2,1) NOT NULL COMMENT '0.0~5.0',
                        PRIMARY KEY (id),
                        KEY IDX_review_user (user_id),
                        KEY IDX_review_market (market_id),
                        CONSTRAINT CHK_review_star CHECK (star >= 0.0 AND star <= 5.0),
                        CONSTRAINT FK_review_users
                            FOREIGN KEY (user_id) REFERENCES users (id)
                                ON UPDATE CASCADE ON DELETE CASCADE,
                        CONSTRAINT FK_review_foodmarket
                            FOREIGN KEY (market_id) REFERENCES foodmarket (id)
                                ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='마켓과 유저 사이의 리뷰';

-- 리뷰 알림
CREATE TABLE review_alarm (
                              id             BIGINT NOT NULL AUTO_INCREMENT,
                              user_id        BIGINT NOT NULL COMMENT '유저 ID',
                              foodmarket_id  BIGINT NOT NULL COMMENT '가게 ID',
                              content        VARCHAR(200) NOT NULL COMMENT '알람 내용(리뷰 관련)',
                              PRIMARY KEY (id),
                              KEY IDX_review_alarm_user (user_id),
                              KEY IDX_review_alarm_market (foodmarket_id),
                              CONSTRAINT FK_review_alarm_users
                                  FOREIGN KEY (user_id) REFERENCES users (id)
                                      ON UPDATE CASCADE ON DELETE CASCADE,
                              CONSTRAINT FK_review_alarm_foodmarket
                                  FOREIGN KEY (foodmarket_id) REFERENCES foodmarket (id)
                                      ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='리뷰 알림';

-- 리뷰 이미지
CREATE TABLE review_image (
                              id         BIGINT NOT NULL AUTO_INCREMENT COMMENT '고유 이미지 ID',
                              review_id  BIGINT NOT NULL COMMENT '리뷰 ID',
                              url        VARCHAR(200) NOT NULL COMMENT '이미지 URL',
                              PRIMARY KEY (id),
                              KEY IDX_review_image_review (review_id),
                              CONSTRAINT FK_review_image_review
                                  FOREIGN KEY (review_id) REFERENCES review (id)
                                      ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='리뷰 이미지들';