package com.sarthak.onlineLearningPlatform.dto.request;

import com.sarthak.onlineLearningPlatform.dto.BaseDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDto extends BaseDto {

	private static final long serialVersionUID = 253815749577647058L;

	@NotBlank(message = "Course title is required")
	private String title;

	@NotBlank(message = "Course description is required")
	@Size(max = 2000, message = "Description cannot exceed 2000 characters")
	private String description;

	@NotNull(message = "Price is required")
	@Min(value = 0, message = "Price cannot be negative")
	private Double price;

	@NotBlank(message = "Duration is required")
	private String duration;

	@NotNull(message = "Total lectures are required")
	@Min(value = 1, message = "Course must contain at least 1 lecture")
	private Integer totalLectures;

	private String assignments;

	private String quizzes;

	private String materials;

	@NotNull(message = "Instructor id is required")
	private Long instructorId;

	@NotNull(message = "School id is required")
	private Long schoolId;

	@NotNull(message = "Category id is required")
	private Long categoryId;
}