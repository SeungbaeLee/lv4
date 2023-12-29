package com.hh99.lv4.domain.like.repository;

import com.hh99.lv4.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByMemberIdAndLectureId(long memberId, long lectureId);
}
