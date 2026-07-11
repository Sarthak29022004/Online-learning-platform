package com.sarthak.onlineLearningPlatform.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarthak.onlineLearningPlatform.dto.request.ProgressRequestDto;
import com.sarthak.onlineLearningPlatform.dto.response.ProgressResponseDto;
import com.sarthak.onlineLearningPlatform.generic.responseHandler.GenericListResponse;
import com.sarthak.onlineLearningPlatform.generic.responseHandler.GenericResponse;
import com.sarthak.onlineLearningPlatform.service.ProgressService;
import com.sarthak.onlineLearningPlatform.swagger.SwaggerConstant;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/olp/progress")
public class ProgressController {
	private final ProgressService progressService;

	public ProgressController(ProgressService progressService) {
		this.progressService = progressService;
	}

	@PostMapping
	@ApiResponses(value = @ApiResponse(responseCode = SwaggerConstant.STATUS_CODE_201, description = SwaggerConstant.CREATE_MESSAGE, content = @Content(examples = @ExampleObject(value = SwaggerConstant.PROGRESS_RESPONSE_MESSAGE))))
	public ResponseEntity<Object> saveProgress(@Valid @RequestBody ProgressRequestDto progressDto) {
		return new ResponseEntity<>(GenericResponse.builder().httpStatus(HttpStatus.CREATED)
				.message(SwaggerConstant.CREATE_MESSAGE).data(progressService.saveProgress(progressDto)).build(),
				HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@ApiResponses(value = @ApiResponse(responseCode = SwaggerConstant.STATUS_CODE_200, description = SwaggerConstant.FETCH_MESSAGE, content = @Content(examples = @ExampleObject(value = SwaggerConstant.PROGRESS_RESPONSE_MESSAGE))))
	public ResponseEntity<Object> getProgressById(@PathVariable Long id) {
		return ResponseEntity.ok(GenericResponse.builder().httpStatus(HttpStatus.OK)
				.data(progressService.getProgressById(id)).message(SwaggerConstant.FETCH_MESSAGE).build());
	}

	@GetMapping
	@ApiResponses(value = @ApiResponse(responseCode = SwaggerConstant.STATUS_CODE_200, description = SwaggerConstant.FETCH_MESSAGE, content = @Content(examples = @ExampleObject(value = SwaggerConstant.PROGRESS_RESPONSE_MESSAGE))))
	public ResponseEntity<Object> getAllProgress() {
		List<ProgressResponseDto> allProgress = progressService.getAllProgress();
		return ResponseEntity.ok(GenericListResponse.builder().httpStatus(HttpStatus.OK).data(allProgress)
				.message(SwaggerConstant.FETCH_MESSAGE).build());
	}

	@PutMapping("/{id}")
	@ApiResponses(value = @ApiResponse(responseCode = SwaggerConstant.STATUS_CODE_200, description = SwaggerConstant.FETCH_MESSAGE, content = @Content(examples = @ExampleObject(value = SwaggerConstant.PROGRESS_RESPONSE_MESSAGE))))
	public ResponseEntity<Object> updateProgress(@PathVariable Long id,
			@Valid @RequestBody ProgressRequestDto progressDto) {
		return ResponseEntity.ok(progressService.updateProgress(id, progressDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProgress(@PathVariable Long id) {
		progressService.deleteProgress(id);
		return ResponseEntity.ok("Progress deleted successfully");
	}
}