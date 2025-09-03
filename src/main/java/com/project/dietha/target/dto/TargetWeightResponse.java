package com.project.dietha.target.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TargetWeightResponse {
    private String message;
    private Double targetWeight;
    private String setDate;
}