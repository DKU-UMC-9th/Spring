package com.example.umc.review;

import com.example.umc.common.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="review_reply")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewReply extends BaseTime {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch=FetchType.LAZY) @JoinColumn(name="review_id", nullable=false, unique=true)
  private Review review;

  @Column(nullable=false, length=1000)
  private String content;
}