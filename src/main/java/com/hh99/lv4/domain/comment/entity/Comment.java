package com.hh99.lv4.domain.comment.entity;

import com.hh99.lv4.domain.lecture.entity.Lecture;
import com.hh99.lv4.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Long parentCommentId;

    private String content;

    @Builder
    public Comment(Long commentId, Lecture lecture, Member member, Long parentCommentId, String content) {
        this.commentId = commentId;
        this.lecture = lecture;
        this.member = member;
        this.parentCommentId = parentCommentId;
        this.content = content;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
