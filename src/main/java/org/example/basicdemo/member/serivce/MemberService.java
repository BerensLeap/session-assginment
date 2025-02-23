package org.example.basicdemo.member.serivce;

import lombok.RequiredArgsConstructor;
import org.example.basicdemo.member.dto.MemberResponseDto;
import org.example.basicdemo.member.dto.MemberSaveRequestDto;
import org.example.basicdemo.member.dto.MemberSaveResponseDto;
import org.example.basicdemo.member.dto.MemberUpdateRequestDto;
import org.example.basicdemo.member.entity.Member;
import org.example.basicdemo.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();

    /*
            List<MemberResponseDto> dtos = new ArrayList<>();
            for (Member member : members) {
                dtos.add(new MemberResponseDto(
                        member.getId(),
                        member.getEmail()
                ));
            }
            return dtos;
            */
       return members.stream().map(member -> new MemberResponseDto(member.getId(), member.getEmail())).toList();
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        return new MemberResponseDto(
                member.getId(),
                member.getEmail()
        );
    }
    @Transactional
    public void update(Long memberId, MemberUpdateRequestDto dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        member.update(dto.getEmail());
    }

    @Transactional
    public void deletedById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
