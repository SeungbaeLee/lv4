package com.hh99.lv4.domain.lecture.dto;

import com.hh99.lv4.domain.comment.entity.Comment;
import com.hh99.lv4.domain.lecture.entity.Category;
import com.hh99.lv4.domain.lecture.entity.Lecture;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LectureResponseDto {

    private Long lectureId;
    private String lectureName;
    private Long price;
    private String introduction;
    private Category category;
    private String instructorName;
    private List<Comment> commentList;
    private int likes;

    @Builder
    public LectureResponseDto(Long lectureId, String lectureName, Long price, String introduction, Category category, String instructorName, List<Comment> commentList,int likes) {
        this.lectureId = lectureId;
        this.lectureName = lectureName;
        this.price = price;
        this.introduction = introduction;
        this.category = category;
        this.instructorName = instructorName;
        this.commentList = commentList;
        this.likes = likes;
    }

    public static LectureResponseDto fromEntity(Lecture lecture) {
        return LectureResponseDto.builder()
                .lectureId(lecture.getLectureId())
                .lectureName(lecture.getLectureName())
                .price(lecture.getPrice())
                .introduction(lecture.getIntroduction())
                .category(lecture.getCategory())
                .instructorName(lecture.getInstructor().getInstructorName())
                .commentList(lecture.getCommentList())
                .likes(lecture.getLikeList().size())
                .build();
    }

    public static List<LectureResponseDto> fromEntityList(List<Lecture> lectureList) {
        return lectureList.stream()
                .map(LectureResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
