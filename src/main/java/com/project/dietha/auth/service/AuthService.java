package com.project.dietha.auth.service;

import com.project.dietha.jwt.JwtTokenProvider;
import com.project.dietha.auth.dto.LoginRequest;
import com.project.dietha.auth.dto.LoginResponse;
import com.project.dietha.auth.dto.SignUpRequest;
import com.project.dietha.auth.dto.SignUpResponse;
import com.project.dietha.auth.entity.User;
import com.project.dietha.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public SignUpResponse signup(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return new SignUpResponse("회원가입 성공", user.getEmail(), user.getUsername());
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtTokenProvider.createToken(user.getEmail(), user.getId());
        return new LoginResponse("로그인 성공", user.getEmail(), user.getUsername(), token);
    }
}
