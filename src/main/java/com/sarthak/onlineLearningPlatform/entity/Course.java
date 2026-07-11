package com.sarthak.onlineLearningPlatform.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8728549500326652936L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseId;

	private String title;

	private String description;

	private Double price;
	private String duration;
	private Integer totalLectures;

	private String assignments;

	private String quizzes;

	private String materials;
	private Boolean deleted = false;

	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;

	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;

	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Progress> progressList = new ArrayList<>();

	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Review> reviews = new ArrayList<>();

	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Storage> storageFiles = new ArrayList<>();
}