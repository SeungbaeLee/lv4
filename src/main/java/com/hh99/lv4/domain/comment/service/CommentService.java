package com.hh99.lv4.domain.comment.service;

import com.hh99.lv4.domain.comment.dto.CommentPatchDto;
import com.hh99.lv4.domain.comment.dto.CommentPostDto;
import com.hh99.lv4.domain.comment.dto.CommentResponseDto;
import com.hh99.lv4.domain.comment.entity.Comment;
import com.hh99.lv4.domain.comment.repository.CommentRepository;
import com.hh99.lv4.domain.lecture.entity.Lecture;
import com.hh99.lv4.domain.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final LectureService lectureService;

    public CommentResponseDto createComment(long lectureId, CommentPostDto postDto) {
        Lecture lecture = lectureService.findLectureById(lectureId);
        Comment comment = Comment.builder()
//                .member(memberId)
                .lecture(lecture)
                .content(postDto.getContent())
                .build();
        commentRepository.save(comment);
        return CommentResponseDto.fromEntity(comment);

    }

    public CommentResponseDto createReply(long lectureId, long parentCommentId, CommentPostDto postDto) {
        Comment parentComment = findCommentById(parentCommentId);
        Lecture lecture = lectureService.findLectureById(lectureId);
        Comment reply = Comment.builder()
                .lecture(lecture)
                .member(parentComment.getMember())
                .parentCommentId(parentCommentId)
                .content(postDto.getContent())
                .build();
        commentRepository.save(reply);
        return CommentResponseDto.fromEntity(reply);

    }

    public CommentResponseDto updateComment(long commentId, CommentPatchDto patchDto) {
        Comment comment = findCommentById(commentId);
        //security memberDetails로 유저 검증 예정
        comment.updateComment(patchDto.getContent());
        return CommentResponseDto.fromEntity(comment);
    }

    public void deleteComment(long commentId) {
        Comment comment = findCommentById(commentId);
        //security memberDetails로 유저 검증 예정
        commentRepository.deleteById(commentId);
    }

    public Comment findCommentById(long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment comment = optionalComment.orElseThrow(() -> new NullPointerException("존재하지 않는 댓글입니다."));
        return comment;
    }
}
