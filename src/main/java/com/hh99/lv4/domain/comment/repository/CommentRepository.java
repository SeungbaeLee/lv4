package com.hh99.lv4.domain.comment.repository;

import com.hh99.lv4.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
