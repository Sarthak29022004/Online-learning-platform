package com.sarthak.onlineLearningPlatform.dto.request;

import com.sarthak.onlineLearningPlatform.dto.BaseDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3473045942721363217L;

	public interface Create {
	}

	public interface Update {
	}

	@NotBlank(message = "Student name is required", groups = { Create.class, Update.class })
	private String name;

	@NotNull
	@Positive
	private Long schoolId;

	@Email(message = "Invalid email format", groups = { Create.class, Update.class })
	@NotBlank(message = "Email is required", groups = { Create.class, Update.class })
	private String email;

	@NotBlank(message = "Password is required", groups = { Create.class, Update.class })
	@Size(min = 6, message = "Password must be at least 6 characters", groups = { Create.class, Update.class })
	private String password;

	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits", groups = { Create.class,
			Update.class })
	private String phone;
}