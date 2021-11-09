package com.profileService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.profileService.entity.AuthRequest;
import com.profileService.repository.AuthProfileRepository;


@Service
public class AuthProfileServiceImpl implements AuthProfileService {
	
	@Autowired
	private AuthProfileRepository repository;
	
	
	private Logger logger = LoggerFactory.getLogger(AuthProfileServiceImpl.class);
	

	

	public AuthRequest getAuthDetails(String email) {
		logger.info("UserName lookup for authentication of "+ email);
		AuthRequest authDetails= repository.findById(email).get();
	
		return authDetails;
	}
}
