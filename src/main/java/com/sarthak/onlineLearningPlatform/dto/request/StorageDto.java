package com.sarthak.onlineLearningPlatform.dto.request;

import java.time.LocalDate;

import com.sarthak.onlineLearningPlatform.dto.BaseDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5388766629442299573L;

	private Long storageId;

	@NotBlank(message = "File name is required")
	private String fileName;

	@NotBlank(message = "File type is required")
	private String fileType;

	@NotBlank(message = "File URL is required")
	private String fileUrl;

	private LocalDate uploadedDate;

	@NotNull(message = "Course id is required")
	private Long courseId;
}
