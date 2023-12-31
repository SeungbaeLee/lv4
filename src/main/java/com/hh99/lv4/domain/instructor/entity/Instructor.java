package com.hh99.lv4.domain.instructor.entity;

import com.hh99.lv4.domain.lecture.entity.Lecture;
import com.hh99.lv4.global.auditing.Auditable;
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
public class Instructor extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;


    private String instructorName;

    private Long years;

    private String company;

    private String phoneNumber;

    private String introduction;
    @OneToMany(mappedBy = "instructor")
    private List<Lecture> lectureList = new ArrayList<>();

    @Builder
    public Instructor(Long instructorId, String instructorName, Long years, String company, String phoneNumber, String introduction, List<Lecture> lectureList) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.years = years;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.lectureList = lectureList;
    }
}
