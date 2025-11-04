package com.example.umc.store;

import com.example.umc.common.BaseTime;
import com.example.umc.user.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name="store")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Store extends BaseTime {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="category_id", nullable=false)
  private FoodCategory category;

  @Column(nullable=false, length=100) private String name;
  @Column(nullable=false, length=255) private String addr;
  @Column(nullable=false) private Boolean dbStatus;

  @OneToMany(mappedBy="store", cascade=CascadeType.ALL, orphanRemoval=true)
  private List<StoreImage> images;
}