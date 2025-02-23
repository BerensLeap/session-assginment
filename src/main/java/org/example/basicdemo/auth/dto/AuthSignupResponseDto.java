package org.example.basicdemo.auth.dto;

import lombok.Getter;

@Getter
public class AuthSignupResponseDto {

    private final String email;

    public AuthSignupResponseDto(String email) {
        this.email = email;
    }
}
