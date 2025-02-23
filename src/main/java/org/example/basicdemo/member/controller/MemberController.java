package org.example.basicdemo.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.basicdemo.common.consts.Const;
import org.example.basicdemo.member.dto.MemberResponseDto;
import org.example.basicdemo.member.dto.MemberSaveRequestDto;
import org.example.basicdemo.member.dto.MemberSaveResponseDto;
import org.example.basicdemo.member.dto.MemberUpdateRequestDto;
import org.example.basicdemo.member.entity.Member;
import org.example.basicdemo.member.serivce.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> getAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberResponseDto> getOne(@PathVariable Long memberId ) {
        return ResponseEntity.ok(memberService.findById(memberId));
    }

    @PutMapping("/members")
    public void update(
            @SessionAttribute(name = Const.LOGIN_USER) Long memberId,
            @RequestBody MemberUpdateRequestDto dto) {
        memberService.update(memberId, dto);
    }

    @DeleteMapping("/members/{memberId}")
    public void delete(
            @SessionAttribute(name = Const.LOGIN_USER) Long memberId) {
        memberService.deletedById(memberId);
    }
}
