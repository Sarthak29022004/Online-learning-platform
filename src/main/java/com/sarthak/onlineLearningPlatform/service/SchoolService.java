package com.sarthak.onlineLearningPlatform.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sarthak.onlineLearningPlatform.dto.request.SchoolDto;
import com.sarthak.onlineLearningPlatform.dto.response.StudentResponseDto;
import com.sarthak.onlineLearningPlatform.entity.School;
import com.sarthak.onlineLearningPlatform.mapper.SchoolMapper;
import com.sarthak.onlineLearningPlatform.mapper.StudentMapper;
import com.sarthak.onlineLearningPlatform.repository.SchoolRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolService {

	private final SchoolRepository schoolRepository;
	private final SchoolMapper schoolMapper;
	private final StudentMapper studentMapper;

	public SchoolDto saveSchool(SchoolDto dto) {

		log.info("Saving school with name: {}", dto.getSchoolName());

		School school = schoolMapper.toEntity(dto);
		school.setDeleted(false);

		School savedSchool = schoolRepository.save(school);

		log.info("School saved successfully with id: {}", savedSchool.getSchoolId());

		return schoolMapper.toDto(savedSchool);
	}

	public SchoolDto getSchoolById(Long id) {

		log.info("Fetching school with id: {}", id);

		School school = schoolRepository.findById(id).filter(s -> !s.getDeleted()).orElseThrow(() -> {
			log.error("School not found with id: {}", id);
			return new RuntimeException("School not found");
		});

		return schoolMapper.toDto(school);
	}

	public Page<SchoolDto> getAllSchools(int page, int size) {

		log.info("Fetching all schools - page: {}, size: {}", page, size);

		Pageable pageable = PageRequest.of(page, size);

		return schoolRepository.findByDeletedFalse(pageable).map(schoolMapper::toDto);
	}

	public SchoolDto updateSchool(Long id, SchoolDto dto) {

		log.info("Updating school with id: {}", id);

		School school = schoolRepository.findById(id).filter(s -> !s.getDeleted()).orElseThrow(() -> {
			log.error("School not found with id: {}", id);
			return new RuntimeException("School not found");
		});

		school.setSchoolName(dto.getSchoolName());
		school.setDescription(dto.getDescription());
		school.setWebsite(dto.getWebsite());

		School updatedSchool = schoolRepository.save(school);

		log.info("School updated successfully with id: {}", updatedSchool.getSchoolId());

		return schoolMapper.toDto(updatedSchool);
	}

	public void deleteSchool(Long id) {

		log.info("Deleting school with id: {}", id);

		School school = schoolRepository.findById(id).filter(s -> !s.getDeleted()).orElseThrow(() -> {
			log.error("School not found with id: {}", id);
			return new RuntimeException("School not found");
		});

		school.setDeleted(true);
		schoolRepository.save(school);

		log.info("School soft deleted successfully with id: {}", id);
	}

	public Page<StudentResponseDto> getStudentsBySchoolId(int page, int size, Long schoolId) {

		log.info("Fetching students for school id: {} with page: {} and size: {}", schoolId, page, size);

		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));

		Page<StudentResponseDto> students = schoolRepository.getStudentsBySchoolId(schoolId, pageable)
				.map(studentMapper::toDto);

		log.info("Fetched {} students for school id: {}", students.getTotalElements(), schoolId);

		return students;
	}
}