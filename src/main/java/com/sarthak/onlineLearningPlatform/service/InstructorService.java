package com.sarthak.onlineLearningPlatform.service;

import com.sarthak.onlineLearningPlatform.specifications.InstructorSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sarthak.onlineLearningPlatform.dto.request.InstructorRequestDto;
import com.sarthak.onlineLearningPlatform.dto.response.InstructorResponseDto;
import com.sarthak.onlineLearningPlatform.entity.Instructor;
import com.sarthak.onlineLearningPlatform.mapper.InstructorMapper;
import com.sarthak.onlineLearningPlatform.repository.InstructorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InstructorService {

	private final InstructorRepository instructorRepository;
	private final InstructorMapper instructorMapper;
	private final InstructorSpecification instructorSpecification;

	public InstructorResponseDto saveInstructor(InstructorRequestDto dto) {

		log.info("Saving instructor with email: {}", dto.getEmail());

		Instructor instructor = instructorMapper.toEntity(dto);
		instructor.setDeleted(false);

		Instructor savedInstructor = instructorRepository.save(instructor);

		log.info("Instructor saved successfully with id: {}", savedInstructor.getInstructorId());

		return instructorMapper.toDto(savedInstructor);
	}

	public InstructorResponseDto getInstructorById(Long id) {

		log.info("Fetching instructor with id: {}", id);

		Instructor instructor = instructorRepository.findById(id).filter(i -> !i.getDeleted()).orElseThrow(() -> {
			log.error("Instructor not found with id: {}", id);
			return new RuntimeException("Instructor not found");
		});

		return instructorMapper.toDto(instructor);
	}

	public Page<InstructorResponseDto> getAllInstructors(final Integer page, final Integer size) {

		log.info("Fetching all instructors");
		
		Pageable pageable = PageRequest.of(page, size);

		Page<InstructorResponseDto> instructors = instructorRepository.findByDeletedFalse(pageable).map(instructorMapper::toDto);

		log.info("Total instructors found: {}", instructors.getTotalElements());

		return instructors;
	}

	public InstructorResponseDto updateInstructor(Long id, InstructorRequestDto dto) {

		log.info("Updating instructor with id: {}", id);

		Instructor instructor = instructorRepository.findById(id).filter(i -> !i.getDeleted()).orElseThrow(() -> {
			log.error("Instructor not found with id: {}", id);
			return new RuntimeException("Instructor not found");
		});

		instructor.setName(dto.getName());
		instructor.setEmail(dto.getEmail());
		instructor.setExpertise(dto.getExpertise());
		instructor.setBio(dto.getBio());

		Instructor updatedInstructor = instructorRepository.save(instructor);

		log.info("Instructor updated successfully with id: {}", updatedInstructor.getInstructorId());

		return instructorMapper.toDto(updatedInstructor);
	}

	public void deleteInstructor(Long id) {

		log.info("Deleting instructor with id: {}", id);

		Instructor instructor = instructorRepository.findById(id).filter(i -> !i.getDeleted()).orElseThrow(() -> {
			log.error("Instructor not found with id: {}", id);
			return new RuntimeException("Instructor not found");
		});

		instructor.setDeleted(true);
		instructorRepository.save(instructor);

		log.info("Instructor soft deleted successfully with id: {}", id);
	}

	public List<InstructorResponseDto> search(String name, String email, String expertise, String bio){
		Specification<Instructor> spec = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

		if(name != null){
			spec = spec.and(instructorSpecification.hasName(name));
		}
		if(email != null){
			spec = spec.and(instructorSpecification.hasEmail(email));
		}
		if(expertise != null){
			spec = spec.and(instructorSpecification.hasExpertise(expertise));
		}
		if(bio != null){
			spec = spec.and(instructorSpecification.hasBio(bio));
		}
        return instructorRepository.findAll(spec).stream().map(instructorMapper::toDto).toList();
	}
}