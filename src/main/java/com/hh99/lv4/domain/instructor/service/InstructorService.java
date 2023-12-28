package com.hh99.lv4.domain.instructor.service;

import com.hh99.lv4.domain.instructor.dto.InstructorPostDto;
import com.hh99.lv4.domain.instructor.dto.InstructorResponseDto;
import com.hh99.lv4.domain.instructor.entity.Instructor;
import com.hh99.lv4.domain.instructor.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    public InstructorResponseDto createInstructor(InstructorPostDto postDto) {
        //Security 적용 후 AuthDetails 값으로 검증
        Instructor instructor = postDto.toEntity();
        Instructor savedInstructor = instructorRepository.save(instructor);
        return InstructorResponseDto.fromEntity(savedInstructor);
    }
}
