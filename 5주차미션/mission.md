// 1번
// JPA
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewsByStoreId(Long storeId);
}

// JPQL
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r JOIN FETCH r.user WHERE r.store.id = :storeId")
    List<Review> findReviewsByStoreId(@Param("storeId") Long storeId);
}


// 2번 
// JPA
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userId); // Optinal은 NULL 방지


// JPQL
@Query("SELECT u FROM User u WHERE u.id = :userId")
Optional<User> findUserById(@Param("userId") Long userId);


// 3번
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("""
        SELECT um FROM UserMission um
        JOIN FETCH um.mission m
        WHERE um.user.id = :userId
          AND um.status IN ('in_progress', 'completed')
        ORDER BY um.updatedAt DESC
    """)
    List<UserMission> findUserMissions(@Param("userId") Long userId, Pageable pageable);
}

// 4번 
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("""
        SELECT um 
        FROM UserMission um
        JOIN FETCH um.mission m
        JOIN FETCH m.store s
        JOIN FETCH s.location l
        WHERE um.user.id = :userId
        ORDER BY um.dDay ASC
    """)
    List<UserMission> findUserHome(@Param("userId") Long userId);
}
