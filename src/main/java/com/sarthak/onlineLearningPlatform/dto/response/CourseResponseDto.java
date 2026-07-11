package com.sarthak.onlineLearningPlatform.dto.response;

import com.sarthak.onlineLearningPlatform.dto.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseResponseDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3913215817881348728L;

	private Long courseId;

	private String title;

	private String description;

	private Double price;

	private String duration;

	private Integer totalLectures;

	private String assignments;

	private String quizzes;

	private String materials;

	private Long instructorId;
	private String instructorName;

	private Long schoolId;
	private String schoolName;

	private Long categoryId;
	private String categoryName;
}
