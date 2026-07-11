package com.sarthak.onlineLearningPlatform.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sarthak.onlineLearningPlatform.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {

	List<Course> findByInstructorInstructorId(Long instructorId);

	List<Course> findBySchoolSchoolId(Long schoolId);

	List<Course> findByCategoryCategoryId(Long categoryId);

	List<Course> findByDeletedFalse();

	Page<Course> findByDeletedFalse(Pageable pageable);

	List<Course> findByTitleIgnoreCase(String title);

	Page<Course> findByTitleContainingIgnoreCaseAndDeletedFalse(String title, Pageable pageable);
}
