package com.sarthak.onlineLearningPlatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sarthak.onlineLearningPlatform.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Page<Category> findByDeletedFalse(Pageable pageable);

	Page<Category> findByCategoryNameContainingIgnoreCaseAndDeletedFalse(Pageable pageable, String categoryName);
}