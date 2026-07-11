package com.sarthak.onlineLearningPlatform.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sarthak.onlineLearningPlatform.entity.School;
import com.sarthak.onlineLearningPlatform.entity.Student;

public interface SchoolRepository extends JpaRepository<School, Long> {

	List<School> findByInstructorInstructorId(Long instructorId);

	Page<School> findByDeletedFalse(Pageable pageable);

	@Query(value = "SELECT * FROM student WHERE school_id = :schoolId", nativeQuery = true)
	Page<Student> getStudentsBySchoolId(@Param("schoolId") Long schoolId, Pageable pageable);

}