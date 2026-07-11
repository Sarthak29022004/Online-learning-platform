package com.sarthak.onlineLearningPlatform.generic.responseHandler;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponse {

	private HttpStatus httpStatus;
	private String message;
	private Object data;
}
