package com.project.dietha.target.repository;

import com.project.dietha.auth.entity.User;
import com.project.dietha.target.entity.TargetWeight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TargetWeightRepository extends JpaRepository<TargetWeight, Long> {
    Optional<TargetWeight> findByUser(User user);
}