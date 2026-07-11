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
public class Storage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6568296773518661232L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long storageId;

	private String fileName;

	private String fileType;

	private String fileUrl;

	private LocalDate uploadedDate;

	private Boolean deleted = false;

	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
}