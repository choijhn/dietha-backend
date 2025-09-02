package com.project.dietha.weight.repository;

import com.project.dietha.weight.entity.Weight;
import com.project.dietha.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

// Weight 엔티티용 JPA Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {
    // 특정 사용자 + 특정 날짜의 체중 기록 찾기
    Optional<Weight> findByUserAndDate(User user, LocalDate date);
}