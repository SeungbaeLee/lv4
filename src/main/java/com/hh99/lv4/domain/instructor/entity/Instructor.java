package com.hh99.lv4.domain.instructor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;

    private String instructorName;

    private Long years;

    private String company;

    private String phoneNumber;

    private String introduction;

    @Builder
    public Instructor(Long instructorId, String instructorName, Long years, String company, String phoneNumber, String introduction) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.years = years;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
    }
}
