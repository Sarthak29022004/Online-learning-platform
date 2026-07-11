package com.sarthak.onlineLearningPlatform.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sarthak.onlineLearningPlatform.dto.request.StorageDto;
import com.sarthak.onlineLearningPlatform.entity.Course;
import com.sarthak.onlineLearningPlatform.entity.Storage;
import com.sarthak.onlineLearningPlatform.mapper.StorageMapper;
import com.sarthak.onlineLearningPlatform.repository.CourseRepository;
import com.sarthak.onlineLearningPlatform.repository.StorageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {

	private final StorageRepository storageRepository;
	private final CourseRepository courseRepository;
	private final Cloudinary cloudinary;
	private final StorageMapper storageMapper;

	public StorageDto uploadFile(MultipartFile file, Long courseId) throws IOException {

		log.info("Uploading file '{}' for course id: {}", file.getOriginalFilename(), courseId);

		Course course = courseRepository.findById(courseId).orElseThrow(() -> {
			log.error("Course not found with id: {}", courseId);
			return new RuntimeException("Course not found");
		});

		byte[] fileBytes = file.getBytes();

		Map<?, ?> uploadResult = cloudinary.uploader().upload(fileBytes,
				ObjectUtils.asMap("resource_type", "auto", "folder", "online-learning-platform"));

		String fileUrl = uploadResult.get("secure_url").toString();

		log.info("File uploaded successfully to Cloudinary");

		Storage storage = new Storage();

		storage.setFileName(file.getOriginalFilename());
		storage.setFileType(file.getContentType());
		storage.setFileUrl(fileUrl);
		storage.setUploadedDate(LocalDate.now());
		storage.setDeleted(false);
		storage.setCourse(course);

		Storage savedStorage = storageRepository.save(storage);

		log.info("File metadata saved successfully with id: {}", savedStorage.getStorageId());

		return storageMapper.toDto(savedStorage);
	}

	public List<StorageDto> getAllFiles() {

		log.info("Fetching all uploaded files");

		List<StorageDto> files = storageRepository.findByDeletedFalse().stream().map(storageMapper::toDto).toList();

		log.info("Total files found: {}", files.size());

		return files;
	}

	public List<StorageDto> getFilesByCourse(Long courseId) {

		log.info("Fetching files for course id: {}", courseId);

		List<StorageDto> files = storageRepository.findByCourseCourseIdAndDeletedFalse(courseId).stream()
				.map(storageMapper::toDto).toList();

		log.info("Found {} files for course id: {}", files.size(), courseId);

		return files;
	}

	public StorageDto getFileById(Long storageId) {

		log.info("Fetching file with id: {}", storageId);

		Storage storage = storageRepository.findById(storageId).filter(s -> !s.getDeleted()).orElseThrow(() -> {
			log.error("File not found with id: {}", storageId);
			return new RuntimeException("File not found");
		});

		return storageMapper.toDto(storage);
	}

	public String deleteFile(Long storageId) {

		log.info("Deleting file with id: {}", storageId);

		Storage storage = storageRepository.findById(storageId).orElseThrow(() -> {
			log.error("File not found with id: {}", storageId);
			return new RuntimeException("File not found");
		});

		storage.setDeleted(true);
		storageRepository.save(storage);

		log.info("File soft deleted successfully with id: {}", storageId);

		return "File deleted successfully";
	}
}