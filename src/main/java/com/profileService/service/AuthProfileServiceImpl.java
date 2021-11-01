package com.profileService.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.profileService.entity.AuthRequest;
import com.profileService.repository.AuthProfileRepository;


@Service
public class AuthProfileServiceImpl implements AuthProfileService {
	
	@Autowired
	private AuthProfileRepository repository;
	

	

	

	public AuthRequest getAuthDetails(String email) {
		System.out.println("Inside get Auth details() ==>"+ email);	
		AuthRequest authDetails= repository.findById(email).get();
	
		return authDetails;
	}
}
