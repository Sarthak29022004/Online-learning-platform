package com.sarthak.onlineLearningPlatform.controller;

import com.sarthak.onlineLearningPlatform.dto.request.StorageDto;
import com.sarthak.onlineLearningPlatform.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/olp/storage")
public class StorageController {

	private final StorageService storageService;

	public StorageController(StorageService storageService) {
		this.storageService = storageService;
	}

	@PostMapping("/upload/{courseId}")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long courseId) {
		try {
			StorageDto storageDto = storageService.uploadFile(file, courseId);
			return ResponseEntity.status(HttpStatus.CREATED).body(storageDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("message", "File upload failed", "error", e.getMessage()));
		}
	}

	@GetMapping
	public ResponseEntity<List<StorageDto>> getAllFiles() {
		return ResponseEntity.ok(storageService.getAllFiles());
	}

	@GetMapping("/{storageId}")
	public ResponseEntity<StorageDto> getFileById(@PathVariable Long storageId) {
		return ResponseEntity.ok(storageService.getFileById(storageId));
	}

	@GetMapping("/course/{courseId}")
	public ResponseEntity<List<StorageDto>> getFilesByCourse(@PathVariable Long courseId) {
		return ResponseEntity.ok(storageService.getFilesByCourse(courseId));
	}

	@DeleteMapping("/{storageId}")
	public ResponseEntity<String> deleteFile(@PathVariable Long storageId) {
		return ResponseEntity.ok(storageService.deleteFile(storageId));
	}
}