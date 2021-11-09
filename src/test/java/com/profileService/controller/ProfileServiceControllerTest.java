package com.profileService.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.profileService.entity.AuthRequest;
import com.profileService.entity.Dependent;
import com.profileService.entity.MemberProfile;
import com.profileService.error.InvalidDataException;
import com.profileService.service.MemberProfileService;

@SpringBootTest
@AutoConfigureMockMvc
class ProfileServiceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MemberProfileService service;
	
	@Autowired
    private WebApplicationContext context;
	
	private MemberProfile profile;
	
	private ObjectMapper mapper = new ObjectMapper();;
	
	@BeforeEach
	void setup() {
		
		Dependent savedDependent1 = new Dependent("R-234", "Morty", "Jerry", "2000-05-02");
		Dependent savedDependent2 = new Dependent("R-456", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> savedDependentsList = new ArrayList<Dependent>();
		
		savedDependentsList.add(savedDependent1);
		savedDependentsList.add(savedDependent2);
		profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				25, "123 Max Street", "Houston", "Texas",
				"United States", "ricky@gmail.com", 
				"9874561230", "BRB123456", "R-123",savedDependentsList );
		mockMvc = MockMvcBuilders
		          .webAppContextSetup(context)
		          .apply(SecurityMockMvcConfigurers.springSecurity())
		          .build();
	}

	@Test
	void addProfile_whenValidData_thenShouldBeAdded() {
		
		Dependent dependent1 = new Dependent("", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile inputProfile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				25, "123 Max Street", "Houston", "Texas",
				"United States", "ricky@gmail.com", 
				"9874561230", "BRB123456789", "",dependentsList );
		
		
		try {
			Mockito.when(service.addMemberProfile(inputProfile)).thenReturn(profile);
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  
		try {
			mockMvc.perform(post("/profile/register")
					.with(SecurityMockMvcRequestPostProcessors.jwt())
					.contentType(MediaType.APPLICATION_JSON)
					
					.content(mapper.writeValueAsString(inputProfile))
					).andDo(print())
			.andExpect(status().isOk());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			   
	}

	@Test
	void getProfile_whenValidData_thenSucceed() {
		
		String email = "ricky@gmail.com";
		
		try {
			Mockito.when(service.getMemberProfile(email)).thenReturn(profile);
		} catch (InvalidDataException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			mockMvc.perform(get("/profile/find/"+email)
				.with(SecurityMockMvcRequestPostProcessors.jwt())
				.contentType(MediaType.APPLICATION_JSON))			
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.memberId").value(profile.getMemberId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
		@Test
		void getProfile_whenInvalidDate_thenExceptionShouldBeThrown() {
			String email = "invalid@gmail.com";
			
			try {
				Mockito.when(service.getMemberProfile(email)).thenThrow(new InvalidDataException("Email not found"));
			} catch (InvalidDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				mockMvc.perform(get("/profile/find/"+email)
					.with(SecurityMockMvcRequestPostProcessors.jwt())
					.contentType(MediaType.APPLICATION_JSON))			
					.andExpect(status().isBadRequest())
					.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidDataException))
					.andExpect(result -> assertEquals("Email not found", result.getResolvedException().getMessage()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	

	@Test
	void updateProfile_whenValidData_thenSucceed() {
		
		try {
			Mockito.when(service.updateMemberProfile(profile)).thenReturn(profile);
		} catch (InvalidDataException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			mockMvc.perform(put("/profile/update")
					.with(SecurityMockMvcRequestPostProcessors.jwt())
					.contentType(MediaType.APPLICATION_JSON)
					
					.content(mapper.writeValueAsString(profile))
					).andDo(print())
			.andExpect(status().isOk());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
