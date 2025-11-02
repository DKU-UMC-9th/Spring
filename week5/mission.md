#### 1. 리뷰 작성

```java
public interface ReviewRepository extends JpaRepository<Review,Long> {
    //save()
}
```
<br>

#### 2. 마이페이지

```java
public interface MemberRepository extends JpaRepository<Member,Long> {
    //findById() 
}
```
<br>

#### 3. 진행중/진행완료 미션

```java
public interface MissionRepository extends JpaRepository<Mission,Long> {
		
    @Query("""
			select m
		  from MemberMission mm
		  join mm.mission m
		  where mm.member.id = :memberId
		    and mm.complete = :complete
		    and (:lastId is null or m.id < :lastId)
		  order by m.id desc
		""")
		Slice<Mission> findMissions( 
				@Param("memberId") Long memberId,
        @Param("complete") boolean complete, //false(진행중), true(진행 완료)       
        @Param("lastId") Long lastId,        //첫 페이지면 null
        Pageable pageable
		);
}
```
<br>

#### 4. 홈 화면 

```java
public interface MissionRepository extends JpaRepository<Mission,Long> {
		
		@EntityGraph(attributePaths = "restaurant")
		@Query("""
			select m
			from Mission m
			join m.restaurant rs 
			join rs.region rg
			where rg.name = :regionName
				and m.expiredAt >= :today
				and (:lastId is null or m.id < :lastId)
			order by m.id desc
		""")
		Slice<Mission> findMissionsByRegion(
		    @Param("regionName") String regionName,
        @Param("today") LocalDate today,   
        @Param("lastId") Long lastId,      // 첫 페이지면 null
        Pageable pageable
		);
}
```
