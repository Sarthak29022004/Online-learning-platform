package com.sarthak.onlineLearningPlatform.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sarthak.onlineLearningPlatform.dto.request.CourseRequestDto;
import com.sarthak.onlineLearningPlatform.dto.response.CourseResponseDto;
import com.sarthak.onlineLearningPlatform.entity.Category;
import com.sarthak.onlineLearningPlatform.entity.Course;
import com.sarthak.onlineLearningPlatform.entity.Instructor;
import com.sarthak.onlineLearningPlatform.entity.School;
import com.sarthak.onlineLearningPlatform.mapper.CourseMapper;
import com.sarthak.onlineLearningPlatform.repository.CategoryRepository;
import com.sarthak.onlineLearningPlatform.repository.CourseRepository;
import com.sarthak.onlineLearningPlatform.repository.InstructorRepository;
import com.sarthak.onlineLearningPlatform.repository.SchoolRepository;
import com.sarthak.onlineLearningPlatform.specifications.CourseSpecification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

	private final CourseRepository courseRepository;
	private final InstructorRepository instructorRepository;
	private final SchoolRepository schoolRepository;
	private final CategoryRepository categoryRepository;
	private final CourseMapper courseMapper;

	public CourseResponseDto createCourse(final CourseRequestDto dto) {

		log.info("Creating course with title: {}", dto.getTitle());

		Instructor instructor = instructorRepository.findById(dto.getInstructorId()).orElseThrow(() -> {
			log.error("Instructor not found with id: {}", dto.getInstructorId());
			return new RuntimeException("Instructor not found");
		});

		School school = schoolRepository.findById(dto.getSchoolId()).orElseThrow(() -> {
			log.error("School not found with id: {}", dto.getSchoolId());
			return new RuntimeException("School not found");
		});

		Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> {
			log.error("Category not found with id: {}", dto.getCategoryId());
			return new RuntimeException("Category not found");
		});

		Course course = new Course();

		course.setTitle(dto.getTitle().trim());
		course.setDescription(dto.getDescription());
		course.setPrice(dto.getPrice());
		course.setDuration(dto.getDuration());
		course.setTotalLectures(dto.getTotalLectures());
		course.setAssignments(dto.getAssignments());
		course.setQuizzes(dto.getQuizzes());
		course.setMaterials(dto.getMaterials());
		course.setDeleted(false);

		course.setInstructor(instructor);
		course.setSchool(school);
		course.setCategory(category);

		Course savedCourse = courseRepository.save(course);

		log.info("Course created successfully with id: {}", savedCourse.getCourseId());

		return courseMapper.toResponseDto(savedCourse);
	}

	public Page<CourseResponseDto> getAllCourses(final Integer page, final Integer size) {

		log.info("Fetching all courses - page: {}, size: {}", page, size);

		Pageable pageable = PageRequest.of(page, size);

		return courseRepository.findByDeletedFalse(pageable).map(courseMapper::toResponseDto);
	}

	public CourseResponseDto getCourseById(Long courseId) {

		log.info("Fetching course with id: {}", courseId);

		Course course = courseRepository.findById(courseId).filter(c -> !c.getDeleted()).orElseThrow(() -> {
			log.error("Course not found with id: {}", courseId);
			return new RuntimeException("Course not found");
		});

		return courseMapper.toResponseDto(course);
	}

	public Page<CourseResponseDto> searchCourseByTitle(final String title, final int page, final int size) {

		log.info("Searching courses by title: {}", title);

		Pageable pageable = PageRequest.of(page, size);

		return courseRepository.findByTitleContainingIgnoreCaseAndDeletedFalse(title, pageable)
				.map(courseMapper::toResponseDto);
	}

	
	public CourseResponseDto updateCourse(final Long courseId, final Long instructorId, final CourseRequestDto dto) {

		log.info("Updating course with id: {}", courseId);

		Course course = courseRepository.findById(courseId).filter(c -> !c.getDeleted()).orElseThrow(() -> {
			log.error("Course not found with id: {}", courseId);
			return new RuntimeException("Course not found");
		});

		if (!course.getInstructor().getInstructorId().equals(instructorId)) {

			log.warn("Unauthorized update attempt by instructor id: {}", instructorId);

			throw new RuntimeException("You are not authorized to update this course");
		}

		course.setTitle(dto.getTitle().trim());
		course.setDescription(dto.getDescription());
		course.setPrice(dto.getPrice());
		course.setDuration(dto.getDuration());
		course.setTotalLectures(dto.getTotalLectures());
		course.setAssignments(dto.getAssignments());
		course.setQuizzes(dto.getQuizzes());
		course.setMaterials(dto.getMaterials());

		Course updatedCourse = courseRepository.save(course);

		log.info("Course updated successfully with id: {}", updatedCourse.getCourseId());

		return courseMapper.toResponseDto(updatedCourse);
	}

	public void removeCourse(final Long courseId, final Long instructorId) {

		log.info("Deleting course with id: {}", courseId);

		Course course = courseRepository.findById(courseId).filter(c -> !c.getDeleted()).orElseThrow(() -> {
			log.error("Course not found with id: {}", courseId);
			return new RuntimeException("Course not found");
		});

		if (!course.getInstructor().getInstructorId().equals(instructorId)) {

			log.warn("Unauthorized delete attempt by instructor id: {}", instructorId);

			throw new RuntimeException("You are not authorized to delete this course");
		}

		course.setDeleted(true);
		courseRepository.save(course);

		log.info("Course soft deleted successfully with id: {}", courseId);
	}

	public List<CourseResponseDto> search(final String title, final Double price) {

		log.info("Searching courses with title: {} and price: {}", title, price);

		Specification<Course> spec = (root, query, builder) -> builder.conjunction();

		if (title != null)
			spec = spec.and(CourseSpecification.hasName(title));

		if (price != null)
			spec = spec.and(CourseSpecification.hasPrice(price));

		List<CourseResponseDto> list = courseRepository.findAll(spec).stream().map(courseMapper::toResponseDto)
				.toList();

		return list;
	}
}