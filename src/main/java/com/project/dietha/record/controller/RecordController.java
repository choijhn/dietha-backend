package com.project.dietha.record.controller;

import com.project.dietha.record.dto.RecordResponse;
import com.project.dietha.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/record")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    // 날짜별 체중 + 식단 조회
    @GetMapping
    public ResponseEntity<RecordResponse> getDailyRecord(
            @RequestParam LocalDate date,
            @AuthenticationPrincipal Long userId
    ) {
        return ResponseEntity.ok(recordService.getDailyRecord(userId, date));
    }
}