package com.project.dietha.meal.service;

import com.project.dietha.auth.entity.User;
import com.project.dietha.auth.repository.UserRepository;
import com.project.dietha.meal.dto.DailyMealRequest;
import com.project.dietha.meal.dto.DailyMealResponse;
import com.project.dietha.meal.dto.MealRequest;
import com.project.dietha.meal.dto.MealResponse;
import com.project.dietha.meal.entity.Meal;
import com.project.dietha.meal.entity.MealType;
import com.project.dietha.meal.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final UserRepository userRepository;

    // 식단 등록 + 수정
    public MealResponse saveOrUpdateMeal(Long userId, MealRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 한국어 → Enum 변환
        MealType mealType = MealType.fromKorean(request.getMealType());

        // 기존 기록 삭제
        List<Meal> existing = mealRepository.findByUserAndDateAndMealType(user, request.getDate(), mealType);
        mealRepository.deleteAll(existing);

        // 새 기록 저장
        List<Meal> meals = request.getFoods().stream()
                .map(food -> Meal.builder()
                        .user(user)
                        .date(request.getDate())
                        .mealType(mealType)
                        .food(food)
                        .build())
                .toList();

        mealRepository.saveAll(meals);

        // 응답은 한국어 끼니명으로 내려줌
        return new MealResponse("식단 기록 저장 성공", request.getDate(), mealType.getKorean(), request.getFoods());
    }

    public DailyMealResponse saveOrUpdateDailyMeal(Long userId, DailyMealRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 요청에 들어온 끼니별 음식 기록 처리
        for (Map.Entry<String, List<String>> entry : request.getMeals().entrySet()) {
            String koreanMealType = entry.getKey();
            List<String> foods = entry.getValue();

            MealType mealType = MealType.fromKorean(koreanMealType);

            // 기존 기록 삭제
            List<Meal> existing = mealRepository.findByUserAndDateAndMealType(user, request.getDate(), mealType);
            mealRepository.deleteAll(existing);

            // 새 기록 저장
            List<Meal> meals = foods.stream()
                    .map(food -> Meal.builder()
                            .user(user)
                            .date(request.getDate())
                            .mealType(mealType)
                            .food(food)
                            .build())
                    .toList();

            mealRepository.saveAll(meals);
        }

        return new DailyMealResponse("하루 식단 기록 저장 성공", request.getDate(), request.getMeals());
    }
}