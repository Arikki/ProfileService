package com.profileService.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.profileService.entity.AuthRequest;


@Service
public class UserService implements UserDetailsService {
    

	
	@Autowired
	private AuthProfileService authProfileSvc;
	
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		AuthRequest authDetails = authProfileSvc.getAuthDetails(username);
		
		
		logger.info("successful lookup of "+ username);
		
		return new User(authDetails.getEmail(),authDetails.getPassword(),new ArrayList<>());
		

	}

}
