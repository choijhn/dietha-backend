package com.project.dietha.record.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class RecordResponse {
    private LocalDate date;
    private Double weight; // 체중
    private Map<String, List<String>> meals; // 끼니별 음식
}
