package com.project.dietha.meal.dto;

import com.project.dietha.meal.entity.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class MealResponse {
    private String message;   // 응답 메시지
    private LocalDate date;   // 날짜
    private String mealType; // 끼니 구분
    private List<String> foods; // 음식 목록
}
