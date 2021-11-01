package com.profileService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dependent {
	
	private String memberId;
	private String firstName;
	private String lastName;
	private String dob;

}
