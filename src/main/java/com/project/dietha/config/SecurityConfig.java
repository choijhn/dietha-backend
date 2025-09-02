package com.project.dietha.config;

import com.project.dietha.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    // SecurityFilterChain: 스프링 시큐리티의 핵심 보안 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthFilter) throws Exception {
        http
                // CORS 기본 설정 허용
                .cors(withDefaults())

                // CSRF 보안 비활성화 (API 서버에서는 보통 꺼둠)
                .csrf(AbstractHttpConfigurer::disable)

                // 요청별 권한 설정
                .authorizeHttpRequests(auth -> auth
                        // 회원가입/로그인은 토큰 없이 허용
                        .requestMatchers("/api/auth/**").permitAll()
                        // 나머지 API는 인증 필요 (토큰 있어야 접근 가능)
                        .anyRequest().authenticated()
                )

                // UsernamePasswordAuthenticationFilter 전에 JWT 필터를 추가
                // → 요청이 들어올 때마다 JWT 검증 먼저 수행
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 비밀번호 암호화를 위한 PasswordEncoder Bean 등록
    // BCrypt: 보안에 강한 해시 함수
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}