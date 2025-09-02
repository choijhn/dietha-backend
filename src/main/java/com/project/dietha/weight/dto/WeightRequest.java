package com.project.dietha.weight.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class WeightRequest {

    @NotNull(message = "날짜 입력 필수")
    private LocalDate date;

    @NotNull(message = "체중 입력 필수")
    private Double weight;
}
