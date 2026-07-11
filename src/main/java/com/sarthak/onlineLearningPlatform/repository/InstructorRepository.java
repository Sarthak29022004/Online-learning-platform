package com.sarthak.onlineLearningPlatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sarthak.onlineLearningPlatform.entity.Instructor;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InstructorRepository extends JpaRepository<Instructor, Long>, JpaSpecificationExecutor<Instructor> {
	Page<Instructor> findByDeletedFalse(Pageable pageable);
}