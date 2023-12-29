package com.hh99.lv4.domain.comment.controller;

import com.hh99.lv4.domain.comment.dto.CommentPatchDto;
import com.hh99.lv4.domain.comment.dto.CommentPostDto;
import com.hh99.lv4.domain.comment.dto.CommentResponseDto;
import com.hh99.lv4.domain.comment.service.CommentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/lectures/{lecture_id}/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity createComment(@PathVariable("lecture_id") @Positive long lectureId,
                                        @RequestBody @Valid CommentPostDto postDto) {
        CommentResponseDto createdComment = commentService.createComment(lectureId, postDto);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PostMapping("/{parent_comment_id}/reply")
    public ResponseEntity createReply(@PathVariable("lecture_id") @Positive long lectureId,
                                      @PathVariable("parent_comment_id") @Positive long parentCommentId,
                                      @RequestBody @Valid CommentPostDto postDto) {
        CommentResponseDto createdReply = commentService.createReply(lectureId, parentCommentId, postDto);
        return new ResponseEntity<>(createdReply, HttpStatus.CREATED);
    }

    @PatchMapping("/{comment_id}")
    public ResponseEntity updateComment(@PathVariable("lecture_id") @Positive long lectureId,
                                        @PathVariable("comment_id") @Positive long commentId,
                                        @RequestBody @Valid CommentPatchDto patchDto) {
        CommentResponseDto updatedComment = commentService.updateComment(commentId,patchDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{comment_id}")
    public ResponseEntity deleteComment(@PathVariable("lecture_id") @Positive long lectureId,
                                        @PathVariable("comment_id") @Positive long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
