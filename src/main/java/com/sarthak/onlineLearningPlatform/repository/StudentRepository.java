package com.sarthak.onlineLearningPlatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sarthak.onlineLearningPlatform.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Page<Student> findByDeletedFalse(Pageable pageable);

	Page<Student> findByNameContainingIgnoreCaseAndDeletedFalse(String name, Pageable pageable);

}