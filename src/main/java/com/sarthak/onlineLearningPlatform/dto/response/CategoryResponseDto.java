package com.sarthak.onlineLearningPlatform.dto.response;

import com.sarthak.onlineLearningPlatform.dto.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryResponseDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9103097086673139791L;

	private Long categoryId;

	private String categoryName;

	private String description;
}