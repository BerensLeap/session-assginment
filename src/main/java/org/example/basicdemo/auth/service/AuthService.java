package org.example.basicdemo.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.basicdemo.auth.dto.AuthLoginRequestDto;
import org.example.basicdemo.auth.dto.AuthLoginResponseDto;
import org.example.basicdemo.auth.dto.AuthSignupRequestDto;
import org.example.basicdemo.member.dto.MemberSaveResponseDto;
import org.example.basicdemo.member.entity.Member;
import org.example.basicdemo.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signup(AuthSignupRequestDto dto) {
        Member member = new Member(dto.getEmail());
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public AuthLoginResponseDto login(AuthLoginRequestDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new IllegalStateException("Member not found")
        );
        return new AuthLoginResponseDto(member.getId());
    }
}
