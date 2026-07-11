package com.sarthak.onlineLearningPlatform.dto.response;

import com.sarthak.onlineLearningPlatform.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressResponseDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8007030470814044507L;

	private Long progressId;

	private Integer completedLectures;

	private Integer totalLectures;

	private Double percentage;

	private String grade;
	private String achievement;

	private Long studentId;

	private Long courseId;
}