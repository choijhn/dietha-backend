package com.project.dietha.meal.entity;

import com.project.dietha.auth.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// 식단 기록 엔티티
@Entity
@Table(name = "meals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meal {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자 (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 날짜
    @Column(nullable = false)
    private LocalDate date;

    // 끼니 구분 (아침/점심/저녁/간식)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MealType mealType;

    // 음식 이름
    @Column(nullable = false)
    private String food;
}