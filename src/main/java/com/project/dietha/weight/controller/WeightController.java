package com.project.dietha.weight.controller;

import com.project.dietha.weight.dto.WeightRequest;
import com.project.dietha.weight.dto.WeightResponse;
import com.project.dietha.weight.service.WeightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/weight")
@RequiredArgsConstructor
public class WeightController {

    private final WeightService weightService;

    // 체중 기록 등록 + 수정 (Upsert)
    @PostMapping
    public ResponseEntity<WeightResponse> saveOrUpdateWeight(
            @Valid @RequestBody WeightRequest request,
            @AuthenticationPrincipal Long userId // JWT에서 추출한 userId
    ) {
        return ResponseEntity.ok(weightService.saveOrUpdateWeight(userId, request));
    }
}