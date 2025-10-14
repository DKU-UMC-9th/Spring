package com.example.umc.store;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="store_image")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class StoreImage {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="store_id", nullable=false)
  private Store store;

  @Column(name="image_url", nullable=false, length=500)
  private String imageUrl;
}