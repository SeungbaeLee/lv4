package com.hh99.lv4.domain.lecture.service;

import com.hh99.lv4.domain.instructor.entity.Instructor;
import com.hh99.lv4.domain.instructor.service.InstructorService;
import com.hh99.lv4.domain.lecture.dto.LecturePostDto;
import com.hh99.lv4.domain.lecture.dto.LectureResponseDto;
import com.hh99.lv4.domain.lecture.entity.Category;
import com.hh99.lv4.domain.lecture.entity.Lecture;
import com.hh99.lv4.domain.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureService {

    private final InstructorService instructorService;
    private final LectureRepository lectureRepository;

    //create
    public LectureResponseDto createLecture(LecturePostDto postDto) {
        Instructor instructor = instructorService.findByName(postDto.getInstructorName());

        Lecture lecture = postDto.toEntity(instructor);
        Lecture savedLecture = lectureRepository.save(lecture);

        return LectureResponseDto.fromEntity(savedLecture);
    }
    //read
    public LectureResponseDto readLecture ( long lectureId){
        Lecture lecture = findLectureById(lectureId);
        return LectureResponseDto.fromEntity(lecture);
    }

    public List<LectureResponseDto> readLectureByCategory(Category category,String sortBy, String orderBy) {
        List<Lecture> lectureList = lectureRepository.findByCategory(category);

        Comparator<Lecture> comparator = null;

        if (sortBy.equals("lectureName")) {
            comparator = Comparator.comparing(Lecture::getLectureName);
        } else if (sortBy.equals("price")) {
            comparator = Comparator.comparing(Lecture::getPrice);
        } else if (sortBy.equals("createdAt")) {
//            comparator = Comparator.comparing(Lecture::getCreateAt);
        }
        if (orderBy.equals("desc")) {
            comparator = comparator.reversed();
        }

        lectureList.sort(comparator);

        return LectureResponseDto.fromEntityList(lectureList);
    }

    public Lecture findLectureById (long lectureId){
        Optional<Lecture> optionalLecture = lectureRepository.findById(lectureId);
        Lecture lecture = optionalLecture.orElseThrow(() -> new RuntimeException("존재하지 않는 강의 입니다."));
        return lecture;
    }
}
