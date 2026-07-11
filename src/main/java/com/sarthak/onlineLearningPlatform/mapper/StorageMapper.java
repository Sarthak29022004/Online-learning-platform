package com.sarthak.onlineLearningPlatform.mapper;

import com.sarthak.onlineLearningPlatform.dto.request.StorageDto;
import com.sarthak.onlineLearningPlatform.entity.Storage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StorageMapper {
	@Mapping(source = "course.courseId", target = "courseId")
	StorageDto toDto(Storage storage);

	@Mapping(source = "courseId", target = "course.courseId")
	Storage toEntity(StorageDto storageDto);
}