-- 1번
-- 가게 리뷰와 유저 정보 조회
SELECT  r.review_id,
        r.star,
        r.content,
        r.created_at,
        u.user_id,
        u.name,
        u.email
FROM Review r
INNER JOIN User u ON r.user_id = u.user_id
WHERE r.store_id = 101;

-- 리뷰 추가
INSERT INTO Review (user_id, store_id, star, content, created_at)
	VALUES (1, 101, 5, '음 너무 맛있어요', NOW());