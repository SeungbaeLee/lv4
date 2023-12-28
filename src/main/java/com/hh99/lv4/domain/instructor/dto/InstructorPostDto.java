package com.hh99.lv4.domain.instructor.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class InstructorPostDto {

    @NonNull
    public String instructorName;
    @NonNull
    public long years;
    @NonNull
    public String company;
    @NonNull
    public String phoneNumber;
    @NonNull
    public String instruction;

    @Builder
    public InstructorPostDto(String instructorName, long years, String company, String phoneNumber, String instruction) {
        this.instructorName = instructorName;
        this.years = years;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.instruction = instruction;
    }
}
