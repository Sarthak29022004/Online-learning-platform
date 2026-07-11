package com.sarthak.onlineLearningPlatform.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor implements Serializable {

	private static final long serialVersionUID = -6814359324572858581L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long instructorId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	private String expertise;

	@Column(name = "deleted", nullable = false)
	private Boolean deleted = false;

	@Column(name = "bio", nullable = false)
	private String bio;

	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Course> courses = new ArrayList<>();

	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
	private List<School> schools = new ArrayList<>();
}