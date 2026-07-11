package com.sarthak.onlineLearningPlatform.dto.request;

import com.sarthak.onlineLearningPlatform.dto.BaseDto;

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
public class SchoolDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1213178708426065981L;

	@NotBlank(message = "School name is required")
	private String schoolName;

	@Size(max = 1000)
	private String description;

	private String website;

	@NotNull(message = "Instructor id is required")
	private Long instructorId;
}