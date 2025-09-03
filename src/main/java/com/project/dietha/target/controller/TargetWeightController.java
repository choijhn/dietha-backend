package com.project.dietha.target.controller;

import com.project.dietha.auth.entity.User;
import com.project.dietha.target.dto.TargetWeightRequest;
import com.project.dietha.target.dto.TargetWeightResponse;
import com.project.dietha.target.service.TargetWeightService;
import com.project.dietha.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/target-weight")
@RequiredArgsConstructor
public class TargetWeightController {

    private final TargetWeightService targetWeightService;
    private final JwtTokenProvider jwtTokenProvider;

    // JWT에서 userId 추출하는 유틸
    private Long getUserIdFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Claims claims = jwtTokenProvider.parseClaims(token);
        return Long.valueOf(claims.get("uid").toString());
    }

    // 목표 체중 저장/수정
    @PostMapping
    public ResponseEntity<TargetWeightResponse> saveTargetWeight(
            HttpServletRequest request,
            @RequestBody TargetWeightRequest targetWeightRequest
    ) {
        Long userId = getUserIdFromToken(request);
        TargetWeightResponse response = targetWeightService.saveTargetWeight(userId, targetWeightRequest);
        return ResponseEntity.ok(response);
    }

    // 목표 체중 조회
    @GetMapping
    public ResponseEntity<TargetWeightResponse> getTargetWeight(HttpServletRequest request) {
        Long userId = getUserIdFromToken(request);
        TargetWeightResponse response = targetWeightService.getTargetWeight(userId);
        return ResponseEntity.ok(response);
    }
}