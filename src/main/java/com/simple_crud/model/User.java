package com.simple_crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "users")
public class User {
	@Id
	@Email
	private String email;
	@Size(min = 4,max = 30)
	@NotBlank
	@NotNull
	private String name;
	@NotBlank
	@NotNull
	@Size(min = 10, max = 13)
	private String contact;
}
