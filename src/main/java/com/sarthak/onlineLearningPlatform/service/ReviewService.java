package com.sarthak.onlineLearningPlatform.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sarthak.onlineLearningPlatform.dto.request.ReviewDto;
import com.sarthak.onlineLearningPlatform.entity.Review;
import com.sarthak.onlineLearningPlatform.mapper.ReviewMapper;
import com.sarthak.onlineLearningPlatform.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final ReviewMapper reviewMapper;

	public ReviewDto saveReview(ReviewDto dto) {

		log.info("Saving review");

		Review review = reviewMapper.toEntity(dto);
		review.setDeleted(false);

		if (review.getReviewDate() == null) {
			review.setReviewDate(LocalDate.now());
			log.info("Review date was null, set to current date");
		}

		Review savedReview = reviewRepository.save(review);

		log.info("Review saved successfully with id: {}", savedReview.getReviewId());

		return reviewMapper.toDto(savedReview);
	}

	public ReviewDto getReviewById(Long id) {

		log.info("Fetching review with id: {}", id);

		Review review = reviewRepository.findById(id).filter(r -> !r.getDeleted()).orElseThrow(() -> {
			log.error("Review not found with id: {}", id);
			return new RuntimeException("Review not found");
		});

		return reviewMapper.toDto(review);
	}

	public List<ReviewDto> getAllReviews() {

		log.info("Fetching all reviews");

		List<ReviewDto> reviews = reviewRepository.findByDeletedFalse().stream().map(reviewMapper::toDto).toList();

		log.info("Total reviews found: {}", reviews.size());

		return reviews;
	}

	public ReviewDto updateReview(Long id, ReviewDto dto) {

		log.info("Updating review with id: {}", id);

		Review review = reviewRepository.findById(id).filter(r -> !r.getDeleted()).orElseThrow(() -> {
			log.error("Review not found with id: {}", id);
			return new RuntimeException("Review not found");
		});

		review.setRating(dto.getRating());
		review.setComment(dto.getComment());
		review.setReviewDate(dto.getReviewDate());

		Review updatedReview = reviewRepository.save(review);

		log.info("Review updated successfully with id: {}", updatedReview.getReviewId());

		return reviewMapper.toDto(updatedReview);
	}

	public void deleteReview(Long id) {

		log.info("Deleting review with id: {}", id);

		Review review = reviewRepository.findById(id).filter(r -> !r.getDeleted()).orElseThrow(() -> {
			log.error("Review not found with id: {}", id);
			return new RuntimeException("Review not found");
		});

		review.setDeleted(true);
		reviewRepository.save(review);

		log.info("Review soft deleted successfully with id: {}", id);
	}
}