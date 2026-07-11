package com.sarthak.onlineLearningPlatform.dto.request;

import java.time.LocalDate;

import com.sarthak.onlineLearningPlatform.dto.BaseDto;

import jakarta.validation.constraints.Max;
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
public class ReviewDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4687209982039709318L;

	private Long reviewId;

	@NotNull(message = "Rating is required")
	@Min(value = 1, message = "Rating must be minimum 1")
	@Max(value = 5, message = "Rating must be maximum 5")
	private Integer rating;

	@NotBlank(message = "Comment is required")
	@Size(max = 1000)
	private String comment;

	private LocalDate reviewDate;

	@NotNull(message = "Student id is required")
	private Long studentId;

	@NotNull(message = "Course id is required")
	private Long courseId;
}