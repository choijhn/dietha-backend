package com.project.dietha.target.service;

import com.project.dietha.auth.entity.User;
import com.project.dietha.auth.repository.UserRepository;
import com.project.dietha.target.dto.TargetWeightRequest;
import com.project.dietha.target.dto.TargetWeightResponse;
import com.project.dietha.target.entity.TargetWeight;
import com.project.dietha.target.repository.TargetWeightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TargetWeightService {

    private final TargetWeightRepository targetWeightRepository;
    private final UserRepository userRepository;

    // 목표 체중 저장/수정
    public TargetWeightResponse saveTargetWeight(Long userId, TargetWeightRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 기존 목표 체중 있으면 수정, 없으면 새로 생성
        TargetWeight targetWeight = targetWeightRepository.findByUser(user)
                .orElse(TargetWeight.builder()
                        .user(user)
                        .build());

        targetWeight.setTargetWeight(request.getTargetWeight());
        targetWeight.setSetDate(LocalDate.now());

        targetWeightRepository.save(targetWeight);

        return new TargetWeightResponse(
                "목표 체중 저장 성공",
                targetWeight.getTargetWeight(),
                targetWeight.getSetDate().toString()
        );
    }

    // 목표 체중 조회 (가장 최근 것)
    public TargetWeightResponse getTargetWeight(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        TargetWeight target = targetWeightRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("목표 체중이 설정되지 않았습니다."));

        return new TargetWeightResponse("목표 체중 조회 성공", target.getTargetWeight(), target.getSetDate().toString());
    }
}