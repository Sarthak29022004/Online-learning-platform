package com.sarthak.onlineLearningPlatform.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4508045004735087453L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;

	private Integer rating;
	private Boolean deleted = false;
	private String comment;
	private LocalDate reviewDate;

	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
}