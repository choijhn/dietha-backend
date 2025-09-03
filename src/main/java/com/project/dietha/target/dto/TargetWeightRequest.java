package com.project.dietha.target.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TargetWeightRequest {
    @NotNull(message = "목표 체중 입력 필수")
    private Double targetWeight;
}