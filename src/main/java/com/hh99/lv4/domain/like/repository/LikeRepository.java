package com.hh99.lv4.domain.like.repository;

import com.hh99.lv4.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query(value = "SELECT * FROM likes WHERE member_id = :memberId AND lecture_id = :lectureId", nativeQuery = true)
    Optional<Like> findByMemberIdAndLectureId(@Param("lectureId") long lectureId, @Param("memberId") long memberId);
}
