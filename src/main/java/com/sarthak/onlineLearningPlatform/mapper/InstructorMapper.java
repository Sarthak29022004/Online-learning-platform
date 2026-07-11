package com.sarthak.onlineLearningPlatform.mapper;

import org.mapstruct.Mapper;

import com.sarthak.onlineLearningPlatform.dto.request.InstructorRequestDto;
import com.sarthak.onlineLearningPlatform.dto.response.InstructorResponseDto;
import com.sarthak.onlineLearningPlatform.entity.Instructor;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

	InstructorResponseDto toDto(Instructor instructor);

	Instructor toEntity(InstructorRequestDto instructorDto);
}