package com.project.dietha.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponse {
    private String message;
    private String email;
    private String username;
}
