package com.hh99.lv4.domain.comment.dto;

import com.hh99.lv4.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long commentId;

    private String content;

    @Builder
    public CommentResponseDto(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }

    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .build();
    }
}
