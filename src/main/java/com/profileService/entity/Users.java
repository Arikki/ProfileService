package com.profileService.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String email;
	private String password;
}