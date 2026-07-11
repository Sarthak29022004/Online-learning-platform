package com.sarthak.onlineLearningPlatform.generic.responseHandler;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericListResponse {

	@Schema(example = "200")
	private HttpStatus httpStatus;
	private String message;
	private Object data;
	private Boolean hasPreviousPage;
	private Boolean hasNextPage;
	private Integer pageSize;
	private Integer pageNumber;
	private Long TotalCount;
}
