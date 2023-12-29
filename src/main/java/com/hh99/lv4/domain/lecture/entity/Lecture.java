package com.hh99.lv4.domain.lecture.entity;

import com.hh99.lv4.domain.comment.entity.Comment;
import com.hh99.lv4.domain.instructor.entity.Instructor;
import com.hh99.lv4.domain.like.entity.Like;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(mappedBy = "lecture")
    private List<Like> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "lecture")
    private List<Comment> commentList = new ArrayList<>();
    private String lectureName;

    private Long price;

    private String introduction;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Builder
    public Lecture(Long lectureId, Instructor instructor,List<Like> likeList, String lectureName, Long price, String introduction, Category category) {
        this.lectureId = lectureId;
        this.instructor = instructor;
        this.likeList = likeList;
        this.lectureName = lectureName;
        this.price = price;
        this.introduction = introduction;
        this.category = category;
    }
}
