package com.profileService.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import com.profileService.entity.AuthRequest;
import com.profileService.repository.AuthProfileRepository;

@SpringBootTest
class AuthProfileServiceImplTest {
	
	@Autowired
	private AuthProfileService service;
	
	@MockBean
	private AuthProfileRepository repository;

	@Test
	void testGetAuthDetails() {
		String email = "ricky@gmail.com";
		AuthRequest basicDetails = new AuthRequest("Rick","Sanchez", "1995-04-01", "ricky@gmail.com", "password");
		
		Mockito.when(repository.findById(email)).thenReturn(Optional.of(basicDetails));
		
		AuthRequest found = service.getAuthDetails(email);
		
		assertEquals(basicDetails.getEmail(),found.getEmail());
		assertEquals(basicDetails.getDateOfBirth(), found.getDateOfBirth());
		assertEquals(basicDetails.getFirstName(), found.getFirstName());
	}

}
