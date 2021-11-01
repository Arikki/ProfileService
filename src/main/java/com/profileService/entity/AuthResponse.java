package com.profileService.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

	private String email;
	private String token;
	private String tokenExpiresIn; //milliseconds

}
