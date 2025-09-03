package com.project.dietha.record.service;

import com.project.dietha.auth.entity.User;
import com.project.dietha.auth.repository.UserRepository;
import com.project.dietha.meal.entity.Meal;
import com.project.dietha.meal.repository.MealRepository;
import com.project.dietha.weight.entity.Weight;
import com.project.dietha.weight.repository.WeightRepository;
import com.project.dietha.record.dto.RecordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final UserRepository userRepository;
    private final WeightRepository weightRepository;
    private final MealRepository mealRepository;

    // 특정 날짜 기준 체중 + 식단 조회
    public RecordResponse getDailyRecord(Long userId, LocalDate date) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 1. 체중 조회
        Weight weight = weightRepository.findByUserAndDate(user, date).orElse(null);

        // 2. 식단 조회
        List<Meal> meals = mealRepository.findByUserAndDate(user, date);

        // 끼니별로 그룹화
        Map<String, List<String>> mealMap = meals.stream()
                .collect(Collectors.groupingBy(
                        meal -> meal.getMealType().getKorean(),   // 한국어 끼니명
                        Collectors.mapping(Meal::getFood, Collectors.toList())
                ));

        return new RecordResponse(date,
                (weight != null) ? weight.getWeight() : null,
                mealMap);
    }
}