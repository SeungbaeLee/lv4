package com.hh99.lv4.domain.instructor.service;

import com.hh99.lv4.domain.instructor.dto.InstructorPostDto;
import com.hh99.lv4.domain.instructor.dto.InstructorResponseDto;
import com.hh99.lv4.domain.instructor.entity.Instructor;
import com.hh99.lv4.domain.instructor.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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


    public Instructor isExistingInstructor(long instructorId) {
        Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorId);
        Instructor instructor = optionalInstructor.orElseThrow(() -> new RuntimeException("존재하지 않는 강사입니다."));
        return instructor;
    }

    public Instructor findByName(String name) {
        Optional<Instructor> optionalInstructor = instructorRepository.findByInstructorName(name);
        Instructor instructor = optionalInstructor.orElseThrow(()-> new RuntimeException("존재하지 않는 강사입니다."));
        return instructor;
    }
}
