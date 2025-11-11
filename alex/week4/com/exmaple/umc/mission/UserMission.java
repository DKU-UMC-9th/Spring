package com.example.umc.mission;

import com.example.umc.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "user_mission",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_user_mission", columnNames = {"user_id", "mission_id"})
    },
    indexes = {
        @Index(name = "idx_um_user", columnList = "user_id"),
        @Index(name = "idx_um_mission", columnList = "mission_id")
    }
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserMission {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mission_id", nullable = false)
  private Mission mission;

  @Column(name = "is_complete", nullable = false)
  private Boolean isComplete = false;

  @Column(name = "complete_at")
  private LocalDateTime completeAt;

  public void completeNow() {
    this.isComplete = true;
    this.completeAt = LocalDateTime.now();
  }
}