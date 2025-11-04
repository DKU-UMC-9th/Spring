package com.example.umc9th.domain.agreement.entity;

import com.example.umc9th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name="user_agreement",
       uniqueConstraints = @UniqueConstraint(name="uq_user_agreement", columnNames={"user_id","agreement_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserAgreement {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id", nullable=false)
  private User user;

  @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="agreement_id", nullable=false)
  private Agreement agreement;

  @Column(nullable=false) private Boolean isAgreed;
  private LocalDateTime agreedAt;
}