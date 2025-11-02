-- 4번
SELECT
    m.title,                
    m.description,          
    m.point,               
    um.is_complete,         
    um.d_day,               
    s.store_name,           
    l.location_name         
FROM
    User_mission um
JOIN
    Mission m ON um.mission_id = m.mission_id
JOIN
    Store s ON m.store_id = s.store_id
JOIN
    Location l ON s.location_id = l.location_id
WHERE
    um.user_id = 1           
ORDER BY
    um.d_day ASC;            -- 디데이 가까운 순서로 정렬
