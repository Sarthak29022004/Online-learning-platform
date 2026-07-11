package com.sarthak.onlineLearningPlatform.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sarthak.onlineLearningPlatform.entity.Course;

@Service
public class CourseSpecification {

	public static Specification<Course> hasName(String title) {
		return (root, query, builder) -> builder.like(builder.lower(root.get("name")), "%" + title.toLowerCase() + "%");
	}

	public static Specification<Course> hasPrice(Double price) {
		return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("price"), price);
	}

}
