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

import com.sarthak.onlineLearningPlatform.dto.request.CategoryRequestDto;
import com.sarthak.onlineLearningPlatform.dto.response.CategoryResponseDto;
import com.sarthak.onlineLearningPlatform.generic.responseHandler.GenericListResponse;
import com.sarthak.onlineLearningPlatform.generic.responseHandler.GenericResponse;
import com.sarthak.onlineLearningPlatform.service.CategoryService;
import com.sarthak.onlineLearningPlatform.swagger.SwaggerConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/olp/categories")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;

	@PostMapping
	@ApiResponses(value = @ApiResponse(responseCode = SwaggerConstant.CREATE_MESSAGE, description = SwaggerConstant.CREATE_MESSAGE, content = @Content(examples = @ExampleObject(value = SwaggerConstant.CATEGORY_RESPONSE_MESSAGE))))
	public ResponseEntity<Object> saveCategory(@Valid @RequestBody CategoryRequestDto categoryDto) {
		return ResponseEntity.ok(GenericResponse.builder()
				.httpStatus(HttpStatus.OK)
				.message("Category created successfully")
				.data(categoryService.saveCategory(categoryDto))
				.build());
	}

	@GetMapping("/search")
	@ApiResponses(value = @ApiResponse(responseCode = SwaggerConstant.STATUS_CODE_200, description = SwaggerConstant.FETCH_MESSAGE, content = @Content(examples = @ExampleObject(value = SwaggerConstant.CATEGORY_LIST_RESPONSE_MESSAGE))))
	public ResponseEntity<Object> getCategoryByName(
			@RequestParam String categoryName,
			@RequestParam(defaultValue = "0") Integer page, 
			@RequestParam(defaultValue = "5") Integer size) {

		Page<CategoryResponseDto> categories = categoryService.getCategoryByName(categoryName, page, size);

		return ResponseEntity.ok(GenericListResponse.builder().httpStatus(HttpStatus.OK)
				.message(SwaggerConstant.FETCH_MESSAGE).data(categories).hasNextPage(categories.hasNext())
				.hasPreviousPage(categories.hasPrevious()).TotalCount(categories.getTotalElements()).build());
	}

	@GetMapping
	@ApiResponses(value = @ApiResponse(responseCode = SwaggerConstant.STATUS_CODE_200, description = SwaggerConstant.FETCH_MESSAGE, content = @Content(examples = @ExampleObject(value = SwaggerConstant.CATEGORY_LIST_RESPONSE_MESSAGE))))
	public ResponseEntity<Object> getAllCategories(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {

		Page<CategoryResponseDto> categoryPage = categoryService.getAllCategories(page, size);

		return ResponseEntity.ok(GenericListResponse.builder().httpStatus(HttpStatus.OK)
				.message("Data fetched Successfully").data(categoryPage).hasNextPage(categoryPage.hasNext())
				.hasPreviousPage(categoryPage.hasPrevious()).TotalCount(categoryPage.getTotalElements()).build());
	}

	@Operation
	@PutMapping("/{id}")
	@ApiResponses(value = @ApiResponse(responseCode = SwaggerConstant.STATUS_CODE_200, description = SwaggerConstant.UPDATE_MESSAGE, content = @Content(examples = @ExampleObject(value = SwaggerConstant.CATEGORY_RESPONSE_MESSAGE))))
	public ResponseEntity<Object> updateCategory(@PathVariable Long id,
			@Valid @RequestBody CategoryRequestDto categoryDto) {

		return ResponseEntity
				.ok(GenericResponse.builder()
						.httpStatus(HttpStatus.OK)
						.message("Category Updated Successfully")
						.data(categoryService.updateCategory(id, categoryDto))
						.build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.ok("Category deleted successfully");
	}
}