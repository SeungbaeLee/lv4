package com.hh99.lv4.domain.like.entity;

import com.hh99.lv4.domain.lecture.entity.Lecture;
import com.hh99.lv4.domain.member.entity.Member;
import com.hh99.lv4.global.auditing.Auditable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "likes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Like(Long likeId, Lecture lecture, Member member) {
        this.likeId = likeId;
        this.lecture = lecture;
        this.member = member;
    }
}
