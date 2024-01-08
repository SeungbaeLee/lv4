package com.hh99.lv4.domain.member.entity;

import com.hh99.lv4.domain.comment.entity.Comment;
import com.hh99.lv4.domain.like.entity.Like;
import com.hh99.lv4.global.auditing.Auditable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @OneToMany(mappedBy = "member")
    private List<Like> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    private String email;

    private String password;

    private String gender;

    private String phoneNumber;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String refreshToken; // 리프레시 토큰

    public List<Role> getRoles() {
        return new ArrayList<>(Collections.singleton(role));
    }

    @Builder
    public Member(Long memberId,List<Like> likeList,List<Comment> commentList, String email, String password, String gender, String phoneNumber, String address, Role role) {
        this.memberId = memberId;
        this.likeList = likeList;
        this.commentList = commentList;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }
}