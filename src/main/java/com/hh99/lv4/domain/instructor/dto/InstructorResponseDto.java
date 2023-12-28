package com.hh99.lv4.domain.instructor.dto;

import com.hh99.lv4.domain.instructor.entity.Instructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InstructorResponseDto {
    public long instructorId;
    public String instructorName;
    public long years;
    public String company;
    public String phoneNumber;
    public String instruction;

    @Builder
    public InstructorResponseDto(long instructorId, String instructorName, long years, String company, String phoneNumber, String instruction) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.years = years;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.instruction = instruction;
    }

    public static InstructorResponseDto fromEntity(Instructor instructor) {
        return InstructorResponseDto.builder()
                .instructorId(instructor.getInstructorId())
                .instructorName(instructor.getInstructorName())
                .years(instructor.getYears())
                .company(instructor.getCompany())
                .phoneNumber(instructor.getPhoneNumber())
                .instruction(instructor.getIntroduction())
                .build();
    }
}