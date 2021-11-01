package com.profileService.controller;




import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.profileService.entity.MemberProfile;

import com.profileService.error.InvalidDataException;

import com.profileService.service.MemberProfileService;




@RestController
@CrossOrigin
public class ProfileServiceController {
	

	

	
	@Autowired
	private MemberProfileService memberProfileSvc;
	


	
	
	

	@PostMapping("/profile/register")
	public MemberProfile addMemberProfile(@RequestBody MemberProfile profile) throws InvalidDataException {
		System.out.println(profile.toString());
		return memberProfileSvc.addMemberProfile(profile);
		
		
	}
	
	
	@GetMapping("/profile/find/{id}")
	public MemberProfile getMemberProfile(@PathVariable ("id") String email) throws InvalidDataException {
		System.out.println("Email in GET request ==>" + email);
		
		return memberProfileSvc.getMemberProfile(email);
	}
	

	@PutMapping("/profile/update")
	public MemberProfile updateMemberProfile(@RequestBody MemberProfile profile ) throws InvalidDataException{
		
		 return memberProfileSvc.updateMemberProfile(profile);
	}
	
	
	
	}
