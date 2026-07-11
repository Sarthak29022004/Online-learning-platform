package com.sarthak.onlineLearningPlatform.dto.request;

import com.sarthak.onlineLearningPlatform.dto.BaseDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto extends BaseDto {

	private static final long serialVersionUID = -1783267266998530709L;

	@NotBlank(message = "Category name is required")
	private String categoryName;

	@Size(max = 1000)
	private String description;
}