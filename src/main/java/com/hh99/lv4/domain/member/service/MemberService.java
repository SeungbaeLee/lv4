package com.hh99.lv4.domain.member.service;

import com.hh99.lv4.domain.member.dto.MemberPostDto;
import com.hh99.lv4.domain.member.dto.MemberResponseDto;
import com.hh99.lv4.domain.member.entity.Member;
import com.hh99.lv4.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto createMember(MemberPostDto postDto) {

//        String encryptedPassword = passwordEncoder.encode(postDto.getPassword());

        Member member = Member.builder()
                .email(postDto.getEmail())
                .password(postDto.getPassword())
                .gender(postDto.getGender())
                .phoneNumber(postDto.getPhoneNumber())
                .address(postDto.getAddress())
                .role(postDto.getRole())
                .build();

        Member savedMember = memberRepository.save(member);
        return MemberResponseDto.fromEntity(savedMember);
    }

    public Member findMemberById(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(() -> new NullPointerException("회원을 찾을 수 없습니다."));
        return member;
    }
}
