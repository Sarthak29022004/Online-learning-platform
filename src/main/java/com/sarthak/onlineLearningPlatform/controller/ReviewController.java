package com.sarthak.onlineLearningPlatform.controller;

import com.sarthak.onlineLearningPlatform.dto.request.ReviewDto;
import com.sarthak.onlineLearningPlatform.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/olp/reviews")
public class ReviewController {
	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@PostMapping
	public ResponseEntity<ReviewDto> saveReview(@Valid @RequestBody ReviewDto reviewDto) {
		return new ResponseEntity<>(reviewService.saveReview(reviewDto), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id) {
		return ResponseEntity.ok(reviewService.getReviewById(id));
	}

	@GetMapping
	public ResponseEntity<?> getAllReviews() {
		return ResponseEntity.ok(reviewService.getAllReviews());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ReviewDto> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewDto reviewDto) {
		return ResponseEntity.ok(reviewService.updateReview(id, reviewDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteReview(@PathVariable Long id) {
		reviewService.deleteReview(id);
		return ResponseEntity.ok("Review deleted successfully");
	}
}