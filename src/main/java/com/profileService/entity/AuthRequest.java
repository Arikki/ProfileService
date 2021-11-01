package com.profileService.entity;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="authDetails")
public class AuthRequest {

	private String firstName;
	private String lastName;
	private String dateOfBirth;
	@Id
	private String email;
	
	private String password;

}
