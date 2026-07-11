package com.sarthak.onlineLearningPlatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sarthak.onlineLearningPlatform.dto.request.StudentRequestDto;
import com.sarthak.onlineLearningPlatform.dto.response.StudentResponseDto;
import com.sarthak.onlineLearningPlatform.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
	@Mapping(source = "school.schoolId", target = "schoolId")
	StudentResponseDto toDto(Student student);

	@Mapping(target = "school.schoolId", source = "schoolId")
	Student toEntity(StudentRequestDto studentDto);
}