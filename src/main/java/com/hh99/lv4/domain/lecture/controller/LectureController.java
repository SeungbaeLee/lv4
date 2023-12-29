package com.hh99.lv4.domain.lecture.controller;

import com.hh99.lv4.domain.lecture.dto.LecturePostDto;
import com.hh99.lv4.domain.lecture.dto.LectureResponseDto;
import com.hh99.lv4.domain.lecture.entity.Category;
import com.hh99.lv4.domain.lecture.service.LectureService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/lectures")
@RestController
public class LectureController {

    private final LectureService lectureService;

    @PostMapping
    public ResponseEntity createLecture(@Valid @RequestBody LecturePostDto lecturePostDto) {
        LectureResponseDto createdLecture = lectureService.createLecture(lecturePostDto);
        return new ResponseEntity<>(createdLecture, HttpStatus.CREATED);
    }

    @GetMapping("/{lectureId}")
    public ResponseEntity readLecture(@PathVariable("lectureId") @Positive long lectureId) {
        LectureResponseDto lectureResponseDto = lectureService.readLecture(lectureId);
        return new ResponseEntity<>(lectureResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity readLectureByCategory(@RequestParam("category") Category category, String sortBy, String orderBy) {
        List<LectureResponseDto> lectureResponseDtoList = lectureService.readLectureByCategory(category,sortBy,orderBy);
        return new ResponseEntity<>(lectureResponseDtoList, HttpStatus.OK);
    }
}
