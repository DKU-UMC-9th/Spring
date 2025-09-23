-- 3번
SELECT
    m.mission_id,
    m.title,
    m.description,
    m.point,
    um.status,
    um.started_at,
    um.completed_at
FROM
    User_missions AS um
INNER JOIN
    Missions AS m ON um.mission_id = m.mission_id  -- 사용자가 이미 시작했거나 완료한 미션들 조회
WHERE
    um.user_id = 현재_사용자_ID
    AND um.status IN ('in_progress', 'completed') -- 진행 중 또는 완료 상태
ORDER BY
    um.updated_at DESC -- 가장 최근에 갱신된 미션부터 정렬
LIMIT
    10 OFFSET 0;