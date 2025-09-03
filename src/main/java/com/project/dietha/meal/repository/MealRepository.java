package com.project.dietha.meal.repository;

import com.project.dietha.auth.entity.User;
import com.project.dietha.meal.entity.Meal;
import com.project.dietha.meal.entity.MealType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    // 특정 사용자 + 날짜 + 끼니에 해당하는 식단 기록 조회
    List<Meal> findByUserAndDateAndMealType(User user, LocalDate date, MealType mealType);
    List<Meal> findByUserAndDate(User user, LocalDate date);
}