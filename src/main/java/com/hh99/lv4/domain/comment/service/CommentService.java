package com.hh99.lv4.domain.comment.service;

import com.hh99.lv4.domain.comment.dto.CommentPatchDto;
import com.hh99.lv4.domain.comment.dto.CommentPostDto;
import com.hh99.lv4.domain.comment.dto.CommentResponseDto;
import com.hh99.lv4.domain.comment.entity.Comment;
import com.hh99.lv4.domain.comment.repository.CommentRepository;
import com.hh99.lv4.domain.lecture.entity.Lecture;
import com.hh99.lv4.domain.lecture.service.LectureService;
import com.hh99.lv4.domain.member.entity.Member;
import com.hh99.lv4.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final LectureService lectureService;
    private final MemberService memberService;

    public CommentResponseDto createComment(long lectureId, CommentPostDto postDto) {
        Lecture lecture = lectureService.findLectureById(lectureId);

        UserDetails loginMember = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = loginMember.getUsername();
        Member foundMember = memberService.findMemberByEmail(email);

        Comment comment = Comment.builder()
                .member(foundMember)
                .lecture(lecture)
                .content(postDto.getContent())
                .build();
        commentRepository.save(comment);
        return CommentResponseDto.fromEntity(comment);

    }

    public CommentResponseDto createReply(long lectureId, long parentCommentId, CommentPostDto postDto) {
        Comment parentComment = findCommentById(parentCommentId);
        Lecture lecture = lectureService.findLectureById(lectureId);

        UserDetails loginMember = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = loginMember.getUsername();
        Member foundMember = memberService.findMemberByEmail(email);

        Comment reply = Comment.builder()
                .lecture(lecture)
                .member(foundMember)
                .parentCommentId(parentCommentId)
                .content(postDto.getContent())
                .build();
        commentRepository.save(reply);
        return CommentResponseDto.fromEntity(reply);

    }

    public CommentResponseDto updateComment(long commentId, CommentPatchDto patchDto) {
        Comment comment = findCommentById(commentId);
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (member.getMemberId() == comment.getMember().getMemberId()) {
            comment.updateComment(patchDto.getContent());
        } else throw new SecurityException("댓글 작성자만 수정 할 수 있습니다.");

        return CommentResponseDto.fromEntity(comment);
    }

    public void deleteComment(long commentId) {
        Comment comment = findCommentById(commentId);
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (member.getMemberId() == comment.getMember().getMemberId()) {
            commentRepository.deleteById(commentId);
        } else throw new SecurityException("댓글 작성자만 삭제 할 수 있습니다.");
    }

    public Comment findCommentById(long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment comment = optionalComment.orElseThrow(() -> new NullPointerException("존재하지 않는 댓글입니다."));
        return comment;
    }
}
