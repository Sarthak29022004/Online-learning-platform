package com.sarthak.onlineLearningPlatform.repository;

import com.sarthak.onlineLearningPlatform.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressRepository extends JpaRepository<Progress, Long> {

    List<Progress> findByStudentStudentId(Long studentId);

    List<Progress> findByCourseCourseId(Long courseId);
    
    List<Progress> findByDeletedFalse();
}