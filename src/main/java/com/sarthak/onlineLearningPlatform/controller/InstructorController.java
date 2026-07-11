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

import com.sarthak.onlineLearningPlatform.dto.request.InstructorRequestDto;
import com.sarthak.onlineLearningPlatform.dto.response.InstructorResponseDto;
import com.sarthak.onlineLearningPlatform.generic.responseHandler.GenericListResponse;
import com.sarthak.onlineLearningPlatform.generic.responseHandler.GenericResponse;
import com.sarthak.onlineLearningPlatform.service.InstructorService;
import com.sarthak.onlineLearningPlatform.swagger.SwaggerConstant;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/olp/instructors")
@RequiredArgsConstructor
public class InstructorController {
	private final InstructorService instructorService;

	@PostMapping
	@ApiResponses(
			value = @ApiResponse(
					responseCode = SwaggerConstant.STATUS_CODE_201,
					description = SwaggerConstant.CREATE_MESSAGE,
					content = @Content(
							examples = @ExampleObject(
									value = SwaggerConstant.INSTRUCTOR_RESPONSE_MESSAGE))))
	public ResponseEntity<GenericResponse> saveInstructor(
			@Valid @RequestBody InstructorRequestDto instructorDto) {
		
		return new ResponseEntity<>(
				GenericResponse.builder()
				.httpStatus(HttpStatus.CREATED)
				.message(SwaggerConstant.CREATE_MESSAGE)
				.data(instructorService.saveInstructor(instructorDto))
				.build(), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@ApiResponses(
			value = @ApiResponse(
					responseCode = SwaggerConstant.STATUS_CODE_200,
					description = SwaggerConstant.FETCH_MESSAGE,
					content = @Content(
							examples = @ExampleObject(
									value = SwaggerConstant.INSTRUCTOR_RESPONSE_MESSAGE))))
	public ResponseEntity<GenericResponse> getInstructorById(@PathVariable Long id) {
		return new ResponseEntity<>(
				GenericResponse.builder()
				.httpStatus(HttpStatus.OK)
				.message(SwaggerConstant.FETCH_MESSAGE)
				.data(instructorService.getInstructorById(id))
				.build(), HttpStatus.OK);
	}
	

	@GetMapping
	@ApiResponses(
			value = @ApiResponse(
					responseCode = SwaggerConstant.STATUS_CODE_200,
					description = SwaggerConstant.FETCH_MESSAGE,
					content = @Content(
							examples = @ExampleObject(
									value = SwaggerConstant.INSTRUCTOR_LIST_RESPONSE_MESSAGE))))
	public ResponseEntity<GenericListResponse> getAllInstructors(
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "5") Integer size) {
		
		Page<InstructorResponseDto> instructors = instructorService.getAllInstructors(page, size);
		
		return ResponseEntity.ok(
				GenericListResponse.builder()
				.httpStatus(HttpStatus.OK)
				.message(SwaggerConstant.FETCH_MESSAGE)
				.data(instructors)
				.hasNextPage(instructors.hasNext())
				.hasPreviousPage(instructors.hasPrevious())
				.TotalCount(instructors.getTotalElements())
				.build());
	}
	

	@PutMapping("/{id}")
	@ApiResponses(
			value = @ApiResponse(
					responseCode = SwaggerConstant.STATUS_CODE_200,
					description = SwaggerConstant.UPDATE_MESSAGE,
					content = @Content(
							examples = @ExampleObject(
									value = SwaggerConstant.INSTRUCTOR_RESPONSE_MESSAGE))))
	public ResponseEntity<GenericResponse> updateInstructor(
			@PathVariable Long id,
			@Valid @RequestBody InstructorRequestDto instructorDto) {
		
		return new ResponseEntity<>(
				GenericResponse.builder()
				.httpStatus(HttpStatus.OK)
				.message(SwaggerConstant.UPDATE_MESSAGE)
				.data(instructorService.updateInstructor(id, instructorDto))
				.build(), HttpStatus.OK);
	}

	// Search Instructor by using Specifications
	@GetMapping("/search")
	public ResponseEntity<GenericResponse> search(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "expertise", required = false) String expertise,
			@RequestParam(value = "bio", required = false) String bio
			){
			return ResponseEntity.ok(GenericResponse.builder()
					.httpStatus(HttpStatus.OK)
					.data(instructorService.search(name, email, expertise, bio))
					.message(SwaggerConstant.FETCH_MESSAGE)
					.build()
			);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInstructor(@PathVariable Long id) {
		instructorService.deleteInstructor(id);
		return ResponseEntity.ok("Instructor deleted successfully");
	}
}