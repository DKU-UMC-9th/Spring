## 1번
```java
public interface ReviewRepository extends JpaRepository<Review, Long> { }
    /*
        Review → 이 repository가 다룰 엔티티 클래스 타입 (@Entity 붙은 클래스)
        Long → 그 엔티티의 식별자(ID) 타입
        즉, “Review 엔티티를 Long 타입 PK로 CRUD 하는 repository”라는 뜻
    */
```

## 2번
```java
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
           select new com.example.umc_spring_first.domain.user.dto.UserProfileDto(
               u.name, u.email, u.phone, u.point
           )
           from User u
           where u.id = :userId
           """)
    Optional<UserProfileDto> findProfileById(@Param("userId") Long userId);
    /*
        Optional : 
        - 행이 있으면 `UserProfileDto`가 들어있는 `Optional`을 반환
        - 없으면 `Optional.empty()` 반환 (null 대신)
        즉, NullPointerException 방지     
    */
}
```

## 3번
```java
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 3번: 내가 진행/완료한 미션 목록 (정렬: m.id DESC)
    @Query("""
        select new com.example.umc_spring_first.domain.mission.dto.MyMissionRowDto(
            m.point, s.name, m.description, um.status
        )
        from UserMission um
        join um.mission m
        join m.store s
        where um.user.id = :userId
          and (:status is null or um.status = :status)
        order by m.id desc
        """)
    Page<MyMissionRowDto> findMyMissions(
            @Param("userId") Long userId,
            @Param("status") String status,   // 'y' / 'n' 등
            Pageable pageable
    );
}
```

## 4번
```java
    public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 4번: 홈 화면 - 진행 가능('n') 미션 목록 (정렬: deadline ASC)
    @Query("""
    select new com.example.umc_spring_first.domain.mission.dto.HomeMissionRowDto(
    m.point, m.description, um.deadline, s.name, s.address
    )
    from UserMission um
    join um.mission m
    join m.store s
    where um.user.id = :userId
    and um.status = :status
    order by um.deadline asc
    """)
    Page<HomeMissionRowDto> findHomeMissions(
    @Param("userId") Long userId,
    @Param("status") String status,   // 예: 'n'
    Pageable pageable                 // PageRequest.of(page, size)
    );
}
```