package com.profileService.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	

	
private Logger logger = LoggerFactory.getLogger(ProfileServiceController.class);

	
	@Autowired
	private MemberProfileService memberProfileSvc;
	


	
	
	

	@PostMapping("/profile/register")
	public MemberProfile addMemberProfile(@RequestBody MemberProfile profile) throws InvalidDataException {
		logger.info("Inside controller to add a profile for user " + profile.getEmail());
		 MemberProfile savedProfile = memberProfileSvc.addMemberProfile(profile);
		 logger.info("Successfully added profile for user " + profile.getEmail());
		return savedProfile;
	}
	
	
	@GetMapping("/profile/find/{id}")
	public MemberProfile getMemberProfile(@PathVariable ("id") String email) throws InvalidDataException {
		logger.info("Inside controller to get the profile of user " + email);
		
		return memberProfileSvc.getMemberProfile(email);
	}
	

	@PutMapping("/profile/update")
	public MemberProfile updateMemberProfile(@RequestBody MemberProfile profile ) throws InvalidDataException{
		logger.info("Inside controller to update the profile for user " + profile.getEmail());
		  MemberProfile savedProfile = memberProfileSvc.updateMemberProfile(profile);
		  logger.info("Successfully updated profile for user " + profile.getEmail());
		 return savedProfile;
	}
	
	
	
	}
