package com.project.dietha.meal.controller;

import com.project.dietha.meal.dto.DailyMealRequest;
import com.project.dietha.meal.dto.DailyMealResponse;
import com.project.dietha.meal.dto.MealRequest;
import com.project.dietha.meal.dto.MealResponse;
import com.project.dietha.meal.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/meal")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    // 식단 기록 등록 + 수정
    @PostMapping
    public ResponseEntity<MealResponse> saveOrUpdateMeal(
            @Valid @RequestBody MealRequest request,
            @AuthenticationPrincipal Long userId // JWT에서 추출한 사용자 ID
    ) {
        return ResponseEntity.ok(mealService.saveOrUpdateMeal(userId, request));
    }

    @PostMapping("/daily")
    public ResponseEntity<DailyMealResponse> saveOrUpdateDailyMeal(
            @Valid @RequestBody DailyMealRequest request,
            @AuthenticationPrincipal Long userId
    ) {
        return ResponseEntity.ok(mealService.saveOrUpdateDailyMeal(userId, request));
    }
}