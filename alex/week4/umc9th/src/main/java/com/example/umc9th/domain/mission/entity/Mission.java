package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.global.common.BaseTime;
import com.example.umc9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(
    name = "mission",
    indexes = {
        @Index(name = "idx_mission_store", columnList = "store_id")
    }
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Mission extends BaseTime {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  @Column(nullable = false, length = 255)
  private String content;

  @Column(nullable = false)
  private LocalDate deadline;

  @Column(nullable = false)
  private Long point;

  @Column(nullable = false)
  @Builder.Default
  private Boolean dbStatus = true;

  @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<UserMission> assignments;
}