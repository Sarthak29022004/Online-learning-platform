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
public class School implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6008513964515023106L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "school_id")
	private Long schoolId;

	private String schoolName;
	private Boolean deleted = false;

	private String description;
	private String website;

	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;

	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
	private List<Course> courses = new ArrayList<>();
}