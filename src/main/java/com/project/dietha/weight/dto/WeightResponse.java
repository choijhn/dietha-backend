package com.project.dietha.weight.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class WeightResponse {
    private String message;
    private LocalDate date;
    private Double weight;
}
