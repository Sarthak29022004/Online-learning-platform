package com.sarthak.onlineLearningPlatform.mapper;

import com.sarthak.onlineLearningPlatform.dto.request.ReviewDto;
import com.sarthak.onlineLearningPlatform.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
	@Mapping(source = "student.studentId", target = "studentId")
	@Mapping(source = "course.courseId", target = "courseId")
	ReviewDto toDto(Review review);

	@Mapping(source = "studentId", target = "student.studentId")
	@Mapping(source = "courseId", target = "course.courseId")
	Review toEntity(ReviewDto reviewDto);
}