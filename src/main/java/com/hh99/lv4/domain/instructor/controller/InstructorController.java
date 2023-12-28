package com.hh99.lv4.domain.instructor.controller;

import com.hh99.lv4.domain.instructor.dto.InstructorPostDto;
import com.hh99.lv4.domain.instructor.dto.InstructorResponseDto;
import com.hh99.lv4.domain.instructor.service.InstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/instructors")
@RestController
public class InstructorController {

    private final InstructorService instructorService;

    @PostMapping
    public ResponseEntity createInstructor(@Valid @RequestBody InstructorPostDto instructorPostDto) {
        InstructorResponseDto createdInstructor = instructorService.createInstructor(instructorPostDto);
        return new ResponseEntity<>(createdInstructor, HttpStatus.CREATED);
    }
}
