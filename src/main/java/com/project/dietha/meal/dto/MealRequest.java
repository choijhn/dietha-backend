package com.project.dietha.meal.dto;

import com.project.dietha.meal.entity.MealType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class MealRequest {
    @NotNull(message = "날짜 입력 필수")
    private LocalDate date;

    @NotNull(message = "끼니 구분 필수")
    private String mealType;

    @NotNull(message = "음식 목록 필수")
    private List<String> foods;
}