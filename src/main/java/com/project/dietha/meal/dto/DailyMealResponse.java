package com.project.dietha.meal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

// 하루치 식단 응답 DTO
@Getter
@AllArgsConstructor
public class DailyMealResponse {
    private String message;
    private LocalDate date;
    private Map<String, List<String>> meals; // 한국어 끼니 -> 음식 리스트
}