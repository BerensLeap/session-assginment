package org.example.basicdemo.auth.dto;

import lombok.Getter;

@Getter
public class AuthLoginResponseDto {

    private final Long MemberId;

    public AuthLoginResponseDto(Long memberId ) {
        MemberId = memberId;
    }
}
