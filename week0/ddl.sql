
CREATE TABLE favorate_food
(
  id      long NOT NULL COMMENT '유저선호음식코드',
  food_id long NOT NULL COMMENT '음식 카테고리를 정하고 함',
  user_id long NOT NULL COMMENT '유저 (id)db내에서의 불변고유식별자',
  PRIMARY KEY (id)
) COMMENT '선호음식';

CREATE TABLE food
(
  id   long        NOT NULL DEFAULT autoincrement COMMENT '음식 카테고리를 정하고 함',
  name VARCHAR(40) NOT NULL COMMENT '음식카테고리 이름',
  PRIMARY KEY (id)
) COMMENT '푸드카테고리';

CREATE TABLE foodmarket
(
  id      long         NOT NULL DEFAULT autoincrement COMMENT '가게 id',
  name    VARCHAR(40)  NOT NULL COMMENT '상호명(유니크는 아님)',
  address VARCHAR(50)  NOT NULL COMMENT '가게주소',
  open    BOOLEAN      NOT NULL COMMENT '영업중이냐?',
  content VARCHAR(200) NOT NULL COMMENT '가계 내용',
  PRIMARY KEY (id)
);

CREATE TABLE mission
(
  id            long         NOT NULL DEFAULT autoincrement COMMENT '고유미션_id',
  market_id     long         NOT NULL COMMENT '가게 id',
  content       VARCHAR(100) NOT NULL COMMENT '미션내용',
  mission_point INTEGER      NOT NULL COMMENT '미션포인트',
  PRIMARY KEY (id)
) COMMENT '마켓과 유저 사이의 미션';

CREATE TABLE mission+user
(
  id         long    NOT NULL DEFAULT autoincrement,
  mission_id long    NOT NULL COMMENT '고유미션_id',
  user_id    long    NOT NULL COMMENT '유저 (id)db내에서의 불변고유식별자',
  content    VARCHAR NOT NULL COMMENT '새 미션에 대한 내용',
  PRIMARY KEY (id)
) COMMENT '신규 미션 받아라 유저!';

CREATE TABLE permission_mission
(
  id         long NOT NULL DEFAULT autoincrement COMMENT '유저 미션할당번호(이거 사장님한테 보여주면 댐)',
  user_id    long NOT NULL COMMENT '유저 (id)db내에서의 불변고유식별자',
  mission_id long NOT NULL COMMENT '고유미션_id',
  PRIMARY KEY (id)
);

CREATE TABLE review
(
  id        long         NOT NULL DEFAULT autoincrement COMMENT '리뷰 고유(id)',
  user_id   long         NOT NULL COMMENT '유저 (id)db내에서의 불변고유식별자',
  market_id long         NOT NULL COMMENT '가게 id',
  content   VARCHAR(100) NOT NULL COMMENT '리뷰내용',
  star      FLOAT        NOT NULL COMMENT '0~5까지',
  PRIMARY KEY (id)
) COMMENT '마켓과 유저사이의 리뷰';

CREATE TABLE review_alram
(
  id            long    NOT NULL DEFAULT autoincrement,
  user_id       long    NOT NULL COMMENT '가게 id',
  foodmarket_id long    NOT NULL COMMENT '유저 (id)db내에서의 불변고유식별자',
  content       VARCHAR NOT NULL COMMENT '알람내용(리뷰관련 내용)',
  PRIMARY KEY (id)
) COMMENT '리뷰해주세용';

CREATE TABLE review_image
(
  id        long         NOT NULL DEFAULT autoincrement COMMENT '고유이미지_id',
  review_id long         NOT NULL COMMENT '리뷰 고유(id)',
  url       VARCHAR(200) NOT NULL COMMENT '이미지 uri',
  PRIMARY KEY (id)
) COMMENT '리뷰에 쓰이는 이미지들';

CREATE TABLE user
(
  id       long        NOT NULL DEFAULT autoincrement COMMENT '유저 (id)db내에서의 불변고유식별자',
  email    VARCHAR(50) NOT NULL COMMENT '로그인할때 이메일(유니크 속성주기)',
  password VARCHAR(30) NULL     COMMENT '8자리이상 영문 숫자 특수문자 조합하셈',
  name     VARCHAR(20) NOT NULL COMMENT '이름임',
  gender   INTEGER     NOT NULL COMMENT '남자0,여자1, 제삼의 성(?)3',
  birth    DATETIME    NOT NULL COMMENT '8자리',
  address  VARCHAR(50) NOT NULL COMMENT '주소',
  point    INTEGER     NOT NULL COMMENT '총 point',
  PRIMARY KEY (id)
) COMMENT 'user_information';

ALTER TABLE review_image
  ADD CONSTRAINT FK_review_TO_review_image
    FOREIGN KEY (review_id)
    REFERENCES review (id);

ALTER TABLE permission_mission
  ADD CONSTRAINT FK_user_TO_permission_mission
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE review
  ADD CONSTRAINT FK_foodmarket_TO_review
    FOREIGN KEY (market_id)
    REFERENCES foodmarket (id);

ALTER TABLE review
  ADD CONSTRAINT FK_user_TO_review
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE mission
  ADD CONSTRAINT FK_foodmarket_TO_mission
    FOREIGN KEY (market_id)
    REFERENCES foodmarket (id);

ALTER TABLE permission_mission
  ADD CONSTRAINT FK_mission_TO_permission_mission
    FOREIGN KEY (mission_id)
    REFERENCES mission (id);

ALTER TABLE favorate_food
  ADD CONSTRAINT FK_food_TO_favorate_food
    FOREIGN KEY (food_id)
    REFERENCES food (id);

ALTER TABLE favorate_food
  ADD CONSTRAINT FK_user_TO_favorate_food
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE review_alram
  ADD CONSTRAINT FK_foodmarket_TO_review_alram
    FOREIGN KEY (user_id)
    REFERENCES foodmarket (id);

ALTER TABLE review_alram
  ADD CONSTRAINT FK_user_TO_review_alram
    FOREIGN KEY (foodmarket_id)
    REFERENCES user (id);

ALTER TABLE mission+user
  ADD CONSTRAINT FK_mission_TO_mission+user
    FOREIGN KEY (mission_id)
    REFERENCES mission (id);

ALTER TABLE mission+user
  ADD CONSTRAINT FK_user_TO_mission+user
    FOREIGN KEY (user_id)
    REFERENCES user (id);
