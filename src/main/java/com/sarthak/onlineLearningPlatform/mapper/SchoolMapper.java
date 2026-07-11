package com.sarthak.onlineLearningPlatform.mapper;

import com.sarthak.onlineLearningPlatform.dto.request.SchoolDto;
import com.sarthak.onlineLearningPlatform.entity.School;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SchoolMapper {
	@Mapping(source = "instructor.instructorId", target = "instructorId")
	SchoolDto toDto(School school);

	@Mapping(source = "instructorId", target = "instructor.instructorId")
	School toEntity(SchoolDto schoolDto);
}