package com.project.dietha.weight.service;

import com.project.dietha.auth.entity.User;
import com.project.dietha.auth.repository.UserRepository;
import com.project.dietha.weight.dto.WeightRequest;
import com.project.dietha.weight.dto.WeightResponse;
import com.project.dietha.weight.entity.Weight;
import com.project.dietha.weight.repository.WeightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeightService {

    private final WeightRepository weightRepository;
    private final UserRepository userRepository;

    // 체중 기록 등록 + 수정 (Upsert)
    public WeightResponse saveOrUpdateWeight(Long userId, WeightRequest request) {
        // 1. 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 2. 해당 날짜의 기록 존재 여부 확인
        Optional<Weight> existingWeight = weightRepository.findByUserAndDate(user, request.getDate());

        Weight weight;
        String message;

        if (existingWeight.isPresent()) {
            // 기존 기록이 있으면 수정
            weight = existingWeight.get();
            weight.setWeight(request.getWeight());
            message = "체중 기록 수정 성공";
        } else {
            // 없으면 새로 등록
            weight = Weight.builder()
                    .user(user)
                    .date(request.getDate())
                    .weight(request.getWeight())
                    .build();
            message = "체중 기록 저장 성공";
        }

        // 3. 저장 (insert or update)
        weightRepository.save(weight);

        // 4. 응답 반환
        return new WeightResponse(message, request.getDate(), request.getWeight());
    }
}