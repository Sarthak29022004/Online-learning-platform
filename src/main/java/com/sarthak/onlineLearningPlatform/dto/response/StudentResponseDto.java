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
public class StudentResponseDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5252217458284663071L;

	private Long studentId;

	private Long schoolId;

	private String name;

	private String email;

	private String password;

	private String phone;
}