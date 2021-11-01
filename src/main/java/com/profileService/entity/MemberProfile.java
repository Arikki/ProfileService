package com.profileService.entity;

import java.util.ArrayList;
import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class MemberProfile {
	
	
	private String firstName;
	private String lastName;
	private String dob;
	private int age;
	private String address;
	private String district;
	private String state;
	private String country;
	@Id
	private String email;
	//private String password;
	private String contactNum;
	private String panCrdNum;
	private String memberId;
	private List<Dependent> dependents=new ArrayList<>();

	
}
