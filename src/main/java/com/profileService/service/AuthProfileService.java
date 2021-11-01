package com.profileService.service;

import com.profileService.entity.AuthRequest;

public interface AuthProfileService {

	
	public AuthRequest getAuthDetails (String email);
	
}
