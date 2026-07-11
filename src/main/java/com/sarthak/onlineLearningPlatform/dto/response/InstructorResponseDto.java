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
public class InstructorResponseDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8321202940642226171L;

	private Long instructorId;

	private String name;

	private String email;

	private String expertise;

	private String bio;
}