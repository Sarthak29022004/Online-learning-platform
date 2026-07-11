package com.sarthak.onlineLearningPlatform.dto.request;

import com.sarthak.onlineLearningPlatform.dto.BaseDto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressRequestDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2585032500855014421L;

	@NotNull(message = "Completed lectures are required")
	@Min(value = 0)
	private Integer completedLectures;

	@NotNull(message = "Total lectures are required")
	@Min(value = 1)
	private Integer totalLectures;

	@NotNull(message = "Percentage is required")
	@DecimalMin(value = "0.0")
	@DecimalMax(value = "100.0")
	private Double percentage;

	private String grade;
	private String achievement;

	@NotNull(message = "Student id is required")
	private Long studentId;

	@NotNull(message = "Course id is required")
	private Long courseId;
}