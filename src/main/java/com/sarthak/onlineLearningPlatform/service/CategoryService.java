package com.sarthak.onlineLearningPlatform.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sarthak.onlineLearningPlatform.dto.request.CategoryRequestDto;
import com.sarthak.onlineLearningPlatform.dto.response.CategoryResponseDto;
import com.sarthak.onlineLearningPlatform.entity.Category;
import com.sarthak.onlineLearningPlatform.mapper.CategoryMapper;
import com.sarthak.onlineLearningPlatform.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;

	public CategoryResponseDto saveCategory(final CategoryRequestDto dto) {
		log.info("Saving new category with name: {}", dto.getCategoryName());

		Category category = new Category();
		BeanUtils.copyProperties(dto, category);

		category.setDeleted(false);

		Category savedCategory = categoryRepository.save(category);
		log.info("Category saved successfully with id: {}", savedCategory.getCategoryId());

		CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
		BeanUtils.copyProperties(savedCategory, categoryResponseDto);

		return categoryResponseDto;
	}

	public Page<CategoryResponseDto> getCategoryByName(final String categoryName, final Integer page,
			final Integer size) {
		log.info("Searching categories by name: {}", categoryName);
		Pageable pageable = PageRequest.of(page, size);
		Page<CategoryResponseDto> categories = categoryRepository
				.findByCategoryNameContainingIgnoreCaseAndDeletedFalse(pageable, categoryName)
				.map(categoryMapper::toDto);
		log.info("Found {} categories", categories.getTotalElements());
		return categories;
	}

	public Page<CategoryResponseDto> getAllCategories(final Integer page, final Integer size) {
		log.info("Fetching all categories");
		Pageable pageable = PageRequest.of(page, size);
		Page<CategoryResponseDto> categories = categoryRepository.findByDeletedFalse(pageable)
				.map(categoryMapper::toDto);
		log.info("Total categories found: {}", categories.getTotalElements());
		return categories;
	}

	public CategoryResponseDto updateCategory(final Long id, final CategoryRequestDto dto) {
		log.info("Updating category with id: {}", id);
		Category category = categoryRepository.findById(id).filter(c -> !c.getDeleted()).orElseThrow(() -> {
			log.error("Category not found with id: {}", id);
			return new RuntimeException("Category not found");
		});
		category.setCategoryName(dto.getCategoryName());
		category.setDescription(dto.getDescription());
		Category updatedCategory = categoryRepository.save(category);
		log.info("Category updated successfully with id: {}", updatedCategory.getCategoryId());
		return categoryMapper.toDto(updatedCategory);
	}

	public void deleteCategory(final Long id) {
		log.info("Deleting category with id: {}", id);
		Category category = categoryRepository.findById(id).filter(c -> !c.getDeleted()).orElseThrow(() -> {
			log.error("Category not found with id: {}", id);
			return new RuntimeException("Category not found");
		});
		category.setDeleted(true);
		categoryRepository.save(category);
		log.info("Category soft deleted successfully with id: {}", id);
	}
}