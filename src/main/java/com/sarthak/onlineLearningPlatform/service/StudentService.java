package com.sarthak.onlineLearningPlatform.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sarthak.onlineLearningPlatform.dto.request.StudentRequestDto;
import com.sarthak.onlineLearningPlatform.dto.response.StudentResponseDto;
import com.sarthak.onlineLearningPlatform.entity.Student;
import com.sarthak.onlineLearningPlatform.mapper.StudentMapper;
import com.sarthak.onlineLearningPlatform.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

	private final StudentRepository studentRepository;
	private final StudentMapper studentMapper;

	public StudentResponseDto saveStudent(StudentRequestDto dto) {

		log.info("Saving student with email: {}", dto.getEmail());

		Student student = studentMapper.toEntity(dto);
		student.setDeleted(false);

		Student savedStudent = studentRepository.save(student);

		log.info("Student saved successfully with id: {}", savedStudent.getStudentId());

		return studentMapper.toDto(savedStudent);
	}

	public StudentResponseDto getStudentById(Long id) {

		log.info("Fetching student with id: {}", id);

		Student student = studentRepository.findById(id).filter(s -> !s.getDeleted()).orElseThrow(() -> {
			log.error("Student not found with id: {}", id);
			return new RuntimeException("Student not found");
		});

		return studentMapper.toDto(student);
	}

	public Page<StudentResponseDto> getAllStudents(int page, int size) {

		log.info("Fetching all students - page: {}, size: {}", page, size);

		Pageable pageable = PageRequest.of(page, size);

		return studentRepository.findByDeletedFalse(pageable).map(studentMapper::toDto);
	}

	public StudentResponseDto updateStudent(Long id, StudentRequestDto dto) {

		log.info("Updating student with id: {}", id);

		Student student = studentRepository.findById(id).filter(s -> !s.getDeleted()).orElseThrow(() -> {
			log.error("Student not found with id: {}", id);
			return new RuntimeException("Student not found");
		});

		student.setName(dto.getName());
		student.setEmail(dto.getEmail());
		student.setPassword(dto.getPassword());
		student.setPhone(dto.getPhone());

		Student updatedStudent = studentRepository.save(student);

		log.info("Student updated successfully with id: {}", updatedStudent.getStudentId());

		return studentMapper.toDto(updatedStudent);
	}

	public void deleteStudent(Long id) {

		log.info("Deleting student with id: {}", id);

		Student student = studentRepository.findById(id).filter(s -> !s.getDeleted()).orElseThrow(() -> {
			log.error("Student not found with id: {}", id);
			return new RuntimeException("Student not found");
		});

		student.setDeleted(true);
		studentRepository.save(student);

		log.info("Student soft deleted successfully with id: {}", id);
	}

	public Page<StudentResponseDto> searchStudentByName(String name, int page, int size) {

		log.info("Searching students by name: {}", name);

		Pageable pageable = PageRequest.of(page, size);

		Page<StudentResponseDto> students = studentRepository
				.findByNameContainingIgnoreCaseAndDeletedFalse(name, pageable).map(studentMapper::toDto);

		log.info("Found {} students for search keyword: {}", students.getTotalElements(), name);

		return students;
	}
}