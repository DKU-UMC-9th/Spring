-- 2번
SELECT 	u.user_id,
		u.email,
		u.name,
		u.point,
		u.phone_num
FROM User AS u
WHERE u.user_id = '1';