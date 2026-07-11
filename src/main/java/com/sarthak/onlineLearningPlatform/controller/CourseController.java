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

import com.sarthak.onlineLearningPlatform.dto.request.CourseRequestDto;
import com.sarthak.onlineLearningPlatform.dto.response.CourseResponseDto;
import com.sarthak.onlineLearningPlatform.generic.responseHandler.GenericListResponse;
import com.sarthak.onlineLearningPlatform.generic.responseHandler.GenericResponse;
import com.sarthak.onlineLearningPlatform.service.CourseService;
import com.sarthak.onlineLearningPlatform.swagger.SwaggerConstant;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/olp/course")
@RequiredArgsConstructor
public class CourseController {

	private final CourseService courseService;


	@PostMapping
	@ApiResponses(value = @ApiResponse(
			responseCode = SwaggerConstant.STATUS_CODE_201, 
			description = SwaggerConstant.CREATE_MESSAGE, 
			content = @Content(
					examples = @ExampleObject(
							value = SwaggerConstant.COURSE_RESPONSE_MESSAGE))))
	public ResponseEntity<Object> createCourse(@Valid @RequestBody CourseRequestDto dto) {
		return ResponseEntity.ok(GenericResponse.builder()
				.httpStatus(HttpStatus.CREATED)
				.message("Course created successfully")
				.data(courseService.createCourse(dto))
				.build());
	}

	@GetMapping
	public ResponseEntity<Object> getAllCourses(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {

		Page<CourseResponseDto> coursePage = courseService.getAllCourses(page, size);

		return ResponseEntity.ok(
				GenericListResponse.builder()
				.httpStatus(HttpStatus.OK)
				.message(SwaggerConstant.FETCH_MESSAGE)
				.data(coursePage)
				.hasNextPage(coursePage.hasNext())
				.hasPreviousPage(coursePage.hasPrevious())
				.TotalCount(coursePage.getTotalElements())
				.build());
	}

	@GetMapping("/{courseId}")
	@ApiResponses(value = @ApiResponse(
			responseCode = SwaggerConstant.STATUS_CODE_200, 
			description = SwaggerConstant.FETCH_MESSAGE, 
			content = @Content(
					examples = @ExampleObject(
							value = SwaggerConstant.COURSE_RESPONSE_MESSAGE))))
	public ResponseEntity<Object> getCourseById(@PathVariable Long courseId) {
		return ResponseEntity.ok(courseService.getCourseById(courseId));
	}

	@GetMapping("/search")
	@ApiResponses(value = @ApiResponse(
			responseCode = SwaggerConstant.STATUS_CODE_200, 
			description = SwaggerConstant.FETCH_MESSAGE, 
			content = @Content(
					examples = @ExampleObject(
							value = SwaggerConstant.COURSE_RESPONSE_MESSAGE))))
	public ResponseEntity<Object> searchCourseByTitle(
			@RequestParam String title,
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "5") int size) {
		return ResponseEntity
				.ok(GenericResponse.builder()
						.httpStatus(HttpStatus.OK)
						.message("Courses Fetched Successfully")
						.data(courseService.searchCourseByTitle(title, page, size))
						.build());
	}

	@PutMapping("/{courseId}/instructor/{instructorId}")
	@ApiResponses(value = @ApiResponse(
			responseCode = SwaggerConstant.STATUS_CODE_200, 
			description = SwaggerConstant.UPDATE_MESSAGE, 
			content = @Content(
					examples = @ExampleObject(
							value = SwaggerConstant.COURSE_RESPONSE_MESSAGE))))
	public ResponseEntity<Object> updateCourse(
			@PathVariable Long courseId, 
			@PathVariable Long instructorId,
			@Valid @RequestBody CourseRequestDto dto) {
		return ResponseEntity
				.ok(GenericResponse.builder()
						.httpStatus(HttpStatus.OK)
						.message("Course Updated Successfully")
						.data(courseService.updateCourse(courseId, instructorId, dto))
						.build());
	}

	@GetMapping("/search-course")
	@ApiResponses(value = @ApiResponse(
			responseCode = SwaggerConstant.STATUS_CODE_200, 
			description = SwaggerConstant.FETCH_MESSAGE, 
			content = @Content(
					examples = @ExampleObject(
							value = SwaggerConstant.COURSE_RESPONSE_MESSAGE))))
	public ResponseEntity<Object> search(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) Double price) {
		return ResponseEntity.ok(
				GenericResponse.builder()
				.httpStatus(HttpStatus.OK)
				.message("Courses fetched successfully")
				.data(courseService.search(title, price))
				.build());
	}
	
	@DeleteMapping("/{courseId}/instructor/{instructorId}")
	public void deleteCourse(
			@PathVariable Long courseId, 
			@PathVariable Long instructorId) {
		courseService.removeCourse(courseId, instructorId);
	}
}
