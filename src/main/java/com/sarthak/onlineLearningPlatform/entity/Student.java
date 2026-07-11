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
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3357327214893847998L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;

	private String name;
	private String email;
	private String password;
	private String phone;
	private Boolean deleted = false;

	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;

	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<Progress> progressList = new ArrayList<>();

	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<Review> reviews = new ArrayList<>();
}