package com.hh99.lv4.domain.instructor.repository;

import com.hh99.lv4.domain.instructor.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
