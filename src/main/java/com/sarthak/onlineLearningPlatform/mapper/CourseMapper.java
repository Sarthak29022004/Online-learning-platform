package com.sarthak.onlineLearningPlatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sarthak.onlineLearningPlatform.dto.request.CourseRequestDto;
import com.sarthak.onlineLearningPlatform.dto.response.CourseResponseDto;
import com.sarthak.onlineLearningPlatform.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {
	@Mapping(source = "courseId", target = "courseId")
	@Mapping(source = "instructor.instructorId", target = "instructorId")
	@Mapping(source = "instructor.name", target = "instructorName")
	@Mapping(source = "school.schoolId", target = "schoolId")
	@Mapping(source = "school.schoolName", target = "schoolName")
	@Mapping(source = "category.categoryId", target = "categoryId")
	@Mapping(source = "category.categoryName", target = "categoryName")
	CourseResponseDto toResponseDto(Course course);

	@Mapping(target = "courseId", ignore = true)
	@Mapping(target = "instructor", ignore = true)
	@Mapping(target = "school", ignore = true)
	@Mapping(target = "category", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	Course toEntity(CourseRequestDto dto);
}