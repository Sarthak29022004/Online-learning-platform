package com.sarthak.onlineLearningPlatform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sarthak.onlineLearningPlatform.dto.request.ProgressRequestDto;
import com.sarthak.onlineLearningPlatform.dto.response.ProgressResponseDto;
import com.sarthak.onlineLearningPlatform.entity.Progress;
import com.sarthak.onlineLearningPlatform.mapper.ProgressMapper;
import com.sarthak.onlineLearningPlatform.repository.ProgressRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgressService {

	private final ProgressRepository progressRepository;
	private final ProgressMapper progressMapper;

	public ProgressResponseDto saveProgress(ProgressRequestDto dto) {

		log.info("Saving progress data");

		Progress progress = progressMapper.toEntity(dto);
		progress.setDeleted(false);

		Progress savedProgress = progressRepository.save(progress);

		log.info("Progress saved successfully with id: {}", savedProgress.getProgressId());

		return progressMapper.toDto(savedProgress);
	}

	public ProgressResponseDto getProgressById(Long id) {

		log.info("Fetching progress with id: {}", id);

		Progress progress = progressRepository.findById(id).filter(p -> !p.getDeleted()).orElseThrow(() -> {
			log.error("Progress not found with id: {}", id);
			return new RuntimeException("Progress not found");
		});

		return progressMapper.toDto(progress);
	}

	public List<ProgressResponseDto> getAllProgress() {

		log.info("Fetching all progress records");

		List<ProgressResponseDto> progressList = progressRepository.findByDeletedFalse().stream()
				.map(progressMapper::toDto).toList();

		log.info("Total progress records found: {}", progressList.size());

		return progressList;
	}

	public ProgressResponseDto updateProgress(Long id, ProgressRequestDto dto) {

		log.info("Updating progress with id: {}", id);

		Progress progress = progressRepository.findById(id).filter(p -> !p.getDeleted()).orElseThrow(() -> {
			log.error("Progress not found with id: {}", id);
			return new RuntimeException("Progress not found");
		});

		progress.setCompletedLectures(dto.getCompletedLectures());
		progress.setTotalLectures(dto.getTotalLectures());
		progress.setPercentage(dto.getPercentage());
		progress.setGrade(dto.getGrade());
		progress.setAchievement(dto.getAchievement());

		Progress updatedProgress = progressRepository.save(progress);

		log.info("Progress updated successfully with id: {}", updatedProgress.getProgressId());

		return progressMapper.toDto(updatedProgress);
	}

	public void deleteProgress(Long id) {

		log.info("Deleting progress with id: {}", id);

		Progress progress = progressRepository.findById(id).filter(p -> !p.getDeleted()).orElseThrow(() -> {
			log.error("Progress not found with id: {}", id);
			return new RuntimeException("Progress not found");
		});

		progress.setDeleted(true);
		progressRepository.save(progress);

		log.info("Progress soft deleted successfully with id: {}", id);
	}
}