package com.sarthak.onlineLearningPlatform.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Progress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7141461336782056826L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long progressId;

	private Integer completedLectures;
	private Integer totalLectures;
	private Double percentage;
	private String grade;
	private String achievement;
	private Boolean deleted = false;

	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
}