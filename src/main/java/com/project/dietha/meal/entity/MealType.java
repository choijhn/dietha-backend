package com.project.dietha.meal.entity;

import lombok.Getter;

@Getter
public enum MealType {
    BREAKFAST("아침"),
    LUNCH("점심"),
    DINNER("저녁"),
    SNACK("간식");

    private final String korean;

    MealType(String korean) {
        this.korean = korean;
    }

    // 한국어로 enum 찾기
    public static MealType fromKorean(String korean) {
        for (MealType type : values()) {
            if (type.korean.equals(korean)) {
                return type;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 끼니 구분: " + korean);
    }
}