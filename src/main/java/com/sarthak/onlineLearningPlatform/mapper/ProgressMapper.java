package com.sarthak.onlineLearningPlatform.mapper;

import com.sarthak.onlineLearningPlatform.dto.request.ProgressRequestDto;
import com.sarthak.onlineLearningPlatform.dto.response.ProgressResponseDto;
import com.sarthak.onlineLearningPlatform.entity.Progress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgressMapper {
	@Mapping(source = "student.studentId", target = "studentId")
	@Mapping(source = "course.courseId", target = "courseId")
	ProgressResponseDto toDto(Progress progress);

	@Mapping(source = "studentId", target = "student.studentId")
	@Mapping(source = "courseId", target = "course.courseId")
	Progress toEntity(ProgressRequestDto progressDto);
}