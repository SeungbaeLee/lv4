package com.hh99.lv4.domain.member.entity;

import com.hh99.lv4.domain.like.entity.Like;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @OneToMany(mappedBy = "member")
    private List<Like> likeList = new ArrayList<>();

    private String email;

    private String password;

    private String gender;

    private String phoneNumber;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(Long memberId,List<Like> likeList, String email, String password, String gender, String phoneNumber, String address, Role role) {
        this.memberId = memberId;
        this.likeList = likeList;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }
}