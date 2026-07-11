package com.sarthak.onlineLearningPlatform.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.sarthak.onlineLearningPlatform.entity.Instructor;
import org.springframework.stereotype.Component;

@Component
public class InstructorSpecification {
	public Specification<Instructor> hasName(String name) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
				"%" + name.toLowerCase() + "%");
	}

	public Specification<Instructor> hasEmail(String email) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("email")),"%"+ email +"%");
	}

	public Specification<Instructor> hasExpertise(String expertise) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("expertise")),"%"+ expertise +"%");
	}

	public Specification<Instructor> hasBio(String bio) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("bio")),"%"+ bio+"%");
	}
}
