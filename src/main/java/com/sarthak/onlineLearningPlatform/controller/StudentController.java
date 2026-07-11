package com.sarthak.onlineLearningPlatform.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sarthak.onlineLearningPlatform.dto.request.StudentRequestDto;
import com.sarthak.onlineLearningPlatform.dto.response.StudentResponseDto;
import com.sarthak.onlineLearningPlatform.generic.responseHandler.GenericResponse;
import com.sarthak.onlineLearningPlatform.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/olp/students")
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping
	public ResponseEntity<GenericResponse> saveStudent(@Valid @RequestBody StudentRequestDto studentDto) {
		return ResponseEntity.ok(GenericResponse.builder().httpStatus(HttpStatus.OK)
				.message("Student added successfully").data(studentService.saveStudent(studentDto)).build());
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id) {
		return ResponseEntity.ok(studentService.getStudentById(id));
	}

	@GetMapping
	public ResponseEntity<GenericResponse> getAllStudents(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		return ResponseEntity.ok(GenericResponse.builder().httpStatus(HttpStatus.OK)
				.data(studentService.getAllStudents(page, size)).build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id,
			@Valid @RequestBody StudentRequestDto studentDto) {
		return ResponseEntity.ok(studentService.updateStudent(id, studentDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return ResponseEntity.ok("Student deleted successfully");
	}

	public ResponseEntity<Page<StudentResponseDto>> searchStudentByName(@RequestParam String name,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
		return ResponseEntity.ok(studentService.searchStudentByName(name, page, size));
	}
}