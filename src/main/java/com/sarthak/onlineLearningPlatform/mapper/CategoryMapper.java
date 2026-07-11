package com.sarthak.onlineLearningPlatform.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.sarthak.onlineLearningPlatform.dto.request.CategoryRequestDto;
import com.sarthak.onlineLearningPlatform.dto.response.CategoryResponseDto;
import com.sarthak.onlineLearningPlatform.entity.Category;

@Component
public class CategoryMapper {

//	CategoryResponseDto toDto(Category category);
//
//	Category toEntity(CategoryRequestDto categoryDto);

	public CategoryResponseDto toDto(Category category) {
		CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
		BeanUtils.copyProperties(category, categoryResponseDto);
		return categoryResponseDto;
	}

	public Category toEntity(CategoryRequestDto categoryRequestDto) {
		Category category = new Category();
		BeanUtils.copyProperties(categoryRequestDto, category);
		return category;
	}
}