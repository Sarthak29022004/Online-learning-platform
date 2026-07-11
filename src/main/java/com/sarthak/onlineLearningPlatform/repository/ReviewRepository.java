package com.sarthak.onlineLearningPlatform.repository;

import com.sarthak.onlineLearningPlatform.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByStudentStudentId(Long studentId);

    List<Review> findByCourseCourseId(Long courseId);
    
    List<Review> findByDeletedFalse();
}