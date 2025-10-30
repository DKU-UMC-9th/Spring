리뷰 작성하는 쿼리

```jsx
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // save()가 이미 구현되어 있음
}
```

마이 페이지 화면 쿼리

```jsx
public interface UserRepository extends JpaRepository<User, Long> {
    // findById()가 이미 구현되어 있음
}
```

내가 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함)

```jsx
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
		@Query("SELECT new com.example.dto.UserMissionInfoDTO(m.id, m.accomplishedAmount, " +
		       "       m.accumulationPoint, m.deadline, s.id, s.name, um.isComplete) " +
		       "FROM UserMission um " + // 테이블명이 아닌 엔티티명
		       "JOIN um.mission m " +    // 엔티티 연관관계로 조인
		       "LEFT JOIN m.store s " + // 엔티티 연관관계로 조인
		       "WHERE um.user.id = :userId " + // um.userId가 아닌 객체 그래프 탐색
		       "ORDER BY um.isComplete ASC, m.deadline ASC")
		Page<UserMissionInfoDTO> findUserMissionsJPQL(
		        @Param("userId") Long userId, 
		        Pageable pageable
		);
}
```

홈 화면 쿼리 (현재 선택 된 지역에서 도전이 가능한 미션 목록, 페이징 포함)

```jsx
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query(value = "SELECT new com.example.dto.AvailableMissionDTO(" + // 1. DTO의 전체 패키지 경로
                   "    m.id, " +
                   "    m.accomplishedAmount, " +
                   "    m.accumulationPoint, " +
                   "    m.deadline, " + // DATE 객체 원본을 DTO 생성자로 전달
                   "    s.id, " +
                   "    s.name, " +
                   "    s.address" +
                   ") " +
                   "FROM Mission m " +
                   "JOIN m.store s " + // 2. 엔티티 연관관계로 조인
                   "LEFT JOIN m.userMissions um WITH um.user.id = :userId " + // 3. 'WITH'로 조인 조건 추가
                   "WHERE m.deadline >= CURRENT_DATE " + // 4. CURDATE() -> CURRENT_DATE
                   "  AND s.address LIKE CONCAT('%', :region, '%') " +
                   "  AND um.id IS NULL " + // 5. um.mission_id IS NULL -> um.id IS NULL
                   "ORDER BY m.deadline ASC") // 6. Pageable이 정렬을 대신할 수 있으나, 쿼리에 명시
    Page<AvailableMissionDTO> findAvailableMissions(
            @Param("userId") Long userId,
            @Param("region") String region,
            Pageable pageable // 7. 'LIMIT', 'OFFSET'을 Pageable로 대체
    );
}
```