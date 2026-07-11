package com.sarthak.onlineLearningPlatform.dto.request;

import com.sarthak.onlineLearningPlatform.dto.BaseDto;

import jakarta.validation.constraints.Email;
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
public class InstructorRequestDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8938903913014719847L;

	@NotBlank(message = "Instructor name is required")
	private String name;

	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Expertise is required")
	private String expertise;

	@Size(max = 1000, message = "Bio must be less than 1000 characters")
	private String bio;
}