package com.project.dietha.meal.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

// 하루치 식단 요청 DTO
@Getter
public class DailyMealRequest {
    @NotNull(message = "날짜는 필수입니다.")
    private LocalDate date;

    // 끼니(한국어) -> 음식 리스트
    private Map<String, List<String>> meals;
}