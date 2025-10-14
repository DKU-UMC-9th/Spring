CREATE DATABASE IF NOT EXISTS umc9th;
USE umc9th;

CREATE TABLE IF NOT EXISTS food_category (
  id        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name      VARCHAR(50)     NOT NULL,
  created_at DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY uq_food_category_name (name)
);

-- #################### User ####################
CREATE TABLE IF NOT EXISTS `User` (
  id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name         VARCHAR(50)     NOT NULL,
  sex          TINYINT(1)      NOT NULL,
  birth        DATE            NOT NULL,
  addr         VARCHAR(255)    NOT NULL,
  user_type    TINYINT(1)      NOT NULL DEFAULT 0,
  social_UID   VARCHAR(255)    NOT NULL,
  social_type  ENUM('KAKAO','NAVER','GOOGLE','APPLE') NOT NULL,
  email        VARCHAR(100)             DEFAULT NULL,
  phone_num    VARCHAR(20)              DEFAULT NULL,
  point        BIGINT UNSIGNED NOT NULL DEFAULT 0,
  db_status    TINYINT(1)      NOT NULL DEFAULT 1,
  created_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY uq_user_social (social_type, social_UID),
  UNIQUE KEY uq_user_email  (email),
  UNIQUE KEY uq_user_phone  (phone_num)
);

CREATE TABLE IF NOT EXISTS agreement (
  id        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '약관 PK',
  content   VARCHAR(1000)   NOT NULL COMMENT '약관 내용',
  created_at DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  updated_at DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_agreement (
  id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id       BIGINT UNSIGNED NOT NULL,
  agreement_id  BIGINT UNSIGNED NOT NULL,
  is_agreed     TINYINT(1)      NOT NULL DEFAULT 0,
  agreed_at     DATETIME                 DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uq_user_agreement (user_id, agreement_id),
  CONSTRAINT fk_useragreement_users
    FOREIGN KEY (user_id) REFERENCES `User`(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_useragreement_agreement
    FOREIGN KEY (agreement_id) REFERENCES agreement(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS user_interest (
  user_id     BIGINT UNSIGNED NOT NULL,
  food_cat_id BIGINT UNSIGNED NOT NULL,

  PRIMARY KEY (user_id, food_cat_id),

  CONSTRAINT fk_userinterest_user
    FOREIGN KEY (user_id) REFERENCES `User`(id)
    ON DELETE CASCADE ON UPDATE CASCADE,

  CONSTRAINT fk_userinterest_foodcat
    FOREIGN KEY (food_cat_id) REFERENCES food_category(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- #################### Store ####################
CREATE TABLE IF NOT EXISTS store (
  id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  category_id BIGINT UNSIGNED NOT NULL,
  name        VARCHAR(100)    NOT NULL,
  addr        VARCHAR(255)    NOT NULL,
  db_status   TINYINT(1)      NOT NULL DEFAULT 1,
  created_at  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_store_category (category_id),
  CONSTRAINT fk_store_category
    FOREIGN KEY (category_id) REFERENCES food_category(id)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CHECK (db_status IN (0,1))
);

CREATE TABLE IF NOT EXISTS store_image (
  id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  store_id   BIGINT UNSIGNED NOT NULL,
  image_url  VARCHAR(500)    NOT NULL,

  PRIMARY KEY (id),
  KEY idx_storeimage_store (store_id),

  CONSTRAINT fk_storeimage_store
    FOREIGN KEY (store_id) REFERENCES store(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- #################### Mission ####################
CREATE TABLE IF NOT EXISTS mission (
  id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  store_id    BIGINT UNSIGNED NOT NULL,
  content     VARCHAR(255)    NOT NULL,
  deadline    DATE            NOT NULL,
  point       BIGINT UNSIGNED NOT NULL DEFAULT 0,
  dbStatus    TINYINT(1)      NOT NULL DEFAULT 1,
  created_at  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (id),
  KEY idx_mission_store (store_id),
  CONSTRAINT fk_mission_store
    FOREIGN KEY (store_id) REFERENCES store(id)
    ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS user_mission (
  id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id      BIGINT UNSIGNED NOT NULL,
  mission_id   BIGINT UNSIGNED NOT NULL,
  is_complete  TINYINT(1)      NOT NULL DEFAULT 0,
  complete_at  DATETIME                 DEFAULT NULL,

  PRIMARY KEY (id),
  UNIQUE KEY uq_user_mission (user_id, mission_id),
  KEY idx_um_user   (user_id),
  KEY idx_um_mission(mission_id),

  CONSTRAINT fk_um_user
    FOREIGN KEY (user_id)    REFERENCES `User`(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_um_mission
    FOREIGN KEY (mission_id) REFERENCES mission(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- #################### Review ####################
CREATE TABLE IF NOT EXISTS review (
  id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id    BIGINT UNSIGNED NOT NULL,
  store_id   BIGINT UNSIGNED NOT NULL,
  star       TINYINT UNSIGNED NOT NULL COMMENT '1~5',
  content    VARCHAR(1000)    NOT NULL,
  db_status  TINYINT(1)       NOT NULL DEFAULT 1,
  created_at DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_review_user  (user_id),
  KEY idx_review_store (store_id),
  CONSTRAINT fk_review_user
    FOREIGN KEY (user_id) REFERENCES `User`(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_review_store
    FOREIGN KEY (store_id) REFERENCES store(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS review_reply (
  id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  review_id  BIGINT UNSIGNED NOT NULL,
  content    VARCHAR(1000)   NOT NULL,
  created_at DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_reviewreply_review (review_id),
  CONSTRAINT fk_reviewreply_review
    FOREIGN KEY (review_id) REFERENCES review(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS review_image (
  id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  review_id  BIGINT UNSIGNED NOT NULL,
  image_url  VARCHAR(500)    NOT NULL,

  PRIMARY KEY (id),
  KEY idx_reviewimage_review (review_id),
  CONSTRAINT fk_reviewimage_review
    FOREIGN KEY (review_id) REFERENCES review(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);