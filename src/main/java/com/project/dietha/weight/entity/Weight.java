package com.project.dietha.weight.entity;

import com.project.dietha.auth.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "weights")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Weight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @ManyToOne(fetch = FetchType.LAZY) // N:1 (여러 체중 기록 → 한 사용자)
    @JoinColumn(name = "user_id", nullable = false) // 외래키(user_id)
    private User user;

    @Column(nullable = false)
    private LocalDate date; // 기록 날짜

    @Column(nullable = false)
    private Double weight; // 체중 값


}
