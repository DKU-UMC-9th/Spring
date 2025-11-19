select * from review where review.market_id=”점포id”;
select (유저란에 있는 관련정보)from user where=(여기에 로그인 인증때 쓴 id값을 넣어줘야함)
select (선택할 정보)from permission_mission as p inner join  mission as m on p.mission_id=m.id inner join food_market as f on m.market_id=f.id where p.user_id=”유저아이디”
limit 10 : 20 (대충 한페이지에 10개씩 담아서 조회하고 지금은 3페이지임;
홈 화면 쿼리(현재 선택 된 지역에서 도전이 가능한 미션 목록, 페이징 포함)   만약에 foodmarket테이블에 위치정보가 있다면 (거리 계산은 단순 차로 계산함)                          
select(정보들) from food_market as f join mission as m on f.id= m.mission_id       
where foodmarket.distance<3              

limit 10 : 20 (대충 한페이지에 10개씩 담아서 조회하고 지금은 3페이지임
select (선택할 정보)
from permission_mission as p 
inner join  mission as m on p.mission_id=m.id 
inner join food_market as f on m.market_id=f.id 
where p.user_id=”유저아이디”
AND (
       points < :p
    OR (m .points = :p AND m.created_at < :t)
    OR (m.points = :p AND m.created_at = :t AND m.id < :m.id)
  )              
order by m.point m.created_at
