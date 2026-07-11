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

import com.sarthak.onlineLearningPlatform.dto.request.SchoolDto;
import com.sarthak.onlineLearningPlatform.generic.responseHandler.GenericResponse;
import com.sarthak.onlineLearningPlatform.service.SchoolService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/olp/schools")
public class SchoolController {
	private final SchoolService schoolService;

	public SchoolController(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

	@PostMapping
	public ResponseEntity<SchoolDto> saveSchool(@Valid @RequestBody SchoolDto schoolDto) {
		return new ResponseEntity<>(schoolService.saveSchool(schoolDto), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SchoolDto> getSchoolById(@PathVariable Long id) {
		return ResponseEntity.ok(schoolService.getSchoolById(id));
	}

	@GetMapping
	public ResponseEntity<Page<SchoolDto>> getAllSchools(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		return ResponseEntity.ok(schoolService.getAllSchools(page, size));
	}

	@PutMapping("/{id}")
	public ResponseEntity<SchoolDto> updateSchool(@PathVariable Long id, @Valid @RequestBody SchoolDto schoolDto) {
		return ResponseEntity.ok(schoolService.updateSchool(id, schoolDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteSchool(@PathVariable Long id) {
		schoolService.deleteSchool(id);
		return ResponseEntity.ok("School deleted successfully");
	}

	@GetMapping("/get-all-students-by-school-id")
	public ResponseEntity<GenericResponse> getStudentsBySchoolId(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2") int size, @RequestParam Long schoolId) {
		return ResponseEntity
				.ok(GenericResponse.builder().httpStatus(HttpStatus.OK).message("Students fetched successfully")
						.data(schoolService.getStudentsBySchoolId(page, size, schoolId)).build());
	}
}