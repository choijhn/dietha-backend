package com.project.dietha.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// 모든 컨트롤러에서 발생하는 예외를 처리하는 전역 핸들러
@ControllerAdvice
public class GlobalExceptionHandler {

    // DTO 유효성 검증 실패(@Valid) 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        // 에러 필드에서 메시지 꺼내오기
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "유효성 검증 실패";

        // 응답 JSON 데이터 구성
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value()); // 상태코드
        body.put("message", errorMessage);                  // 에러 메시지
        body.put("path", request.getRequestURI());          // 요청 경로
        body.put("timestamp", LocalDateTime.now().toString()); // 발생 시간

        return ResponseEntity.badRequest().body(body);
    }

    // 서비스 로직에서 발생하는 RuntimeException 처리
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(
            RuntimeException ex,
            HttpServletRequest request
    ) {
        // 응답 JSON 데이터 구성
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value()); // 상태코드
        body.put("message", ex.getMessage());               // 예외 메시지
        body.put("path", request.getRequestURI());          // 요청 경로
        body.put("timestamp", LocalDateTime.now().toString()); // 발생 시간

        return ResponseEntity.badRequest().body(body);
    }
}