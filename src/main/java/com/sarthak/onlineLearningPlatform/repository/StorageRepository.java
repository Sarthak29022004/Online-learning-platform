package com.sarthak.onlineLearningPlatform.repository;

import com.sarthak.onlineLearningPlatform.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {

    List<Storage> findByDeletedFalse();

    List<Storage> findByCourseCourseIdAndDeletedFalse(Long courseId);
}