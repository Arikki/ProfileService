package com.profileService.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.profileService.entity.Dependent;
import com.profileService.entity.MemberProfile;
import com.profileService.error.InvalidDataException;
import com.profileService.repository.MemberProfileRepository;

@SpringBootTest
class MemberProfileServiceTest {
	
	@Autowired
	private MemberProfileService memberProfileSvc;
	
	
	
	@MockBean
	private MemberProfileRepository repository;
	
	@BeforeEach
	void setup() {
	
		
		Dependent dependent1 = new Dependent("R-234", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("R-456", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				25, "123 Max Street", "Houston", "Texas",
				"United States", "ricky@gmail.com", 
				"9874561230", "BRB123456", "R-123",dependentsList );
		
		Mockito.when(repository.findById("ricky@gmail.com")).thenReturn(Optional.of(profile));
		
		
		
	}
	
	@Test
	void addProfile_whenValidData_profileShoudlBeAdded() {
		
		Dependent dependent1 = new Dependent("", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				25, "123 Max Street", "Houston", "Texas",
				"United States", "ricky@gmail.com", 
				"9874561230", "BRB123456789", "",dependentsList );
		
		Dependent savedDependent1 = new Dependent("R-234", "Morty", "Jerry", "2000-05-02");
		Dependent savedDependent2 = new Dependent("R-456", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> savedDependentsList = new ArrayList<Dependent>();
		
		savedDependentsList.add(savedDependent1);
		savedDependentsList.add(savedDependent2);
		MemberProfile savedProfile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				25, "123 Max Street", "Houston", "Texas",
				"United States", "ricky@gmail.com", 
				"9874561230", "BRB123456", "R-123",savedDependentsList );
		Mockito.when(repository.insert(profile)).thenReturn(savedProfile);
		
		MemberProfile saved = null;
		try {
			saved = memberProfileSvc.addMemberProfile(profile);
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(saved.getMemberId(),"R-123");
		assertEquals(saved.getDependents().get(0).getMemberId(), "R-234");
		assertEquals(saved.getDependents().get(1).getMemberId(), "R-456");
		
		
	}
	
	@Test
	void addProfile_whenInvalidAge_exceptionShouldBeThrown() {
		
		Dependent dependent1 = new Dependent("", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				17, "123 Max Street", "Houston", "Texas",
				"United States", "ricky@gmail.com", 
				"9874561230", "BRB123456789", "",dependentsList );
		String expectedMsg = "Age must be greater than 18";
		Exception exception = assertThrows(InvalidDataException.class, ()->{
			memberProfileSvc.addMemberProfile(profile);
		});
		
		assertTrue(expectedMsg.contains(exception.getMessage()));
	
	}

	@Test
	void addProfile_whenInvalidEmail_exceptionShouldBeThrown() {
		
		Dependent dependent1 = new Dependent("", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				21, "123 Max Street", "Houston", "Texas",
				"United States", "rickygmail.com", 
				"9874561230", "BRB123456789", "",dependentsList );
		String expectedMsg = "Email Id must contain '@' and '.'";
		Exception exception = assertThrows(InvalidDataException.class, ()->{
			memberProfileSvc.addMemberProfile(profile);
		});
		
		assertTrue(expectedMsg.contains(exception.getMessage()));
	
	}
	
	@Test
	void addProfile_whenEmailIdWithoutDot_exceptionShouldBeThrown() {
		
		Dependent dependent1 = new Dependent("", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				21, "123 Max Street", "Houston", "Texas",
				"United States", "ricky@gmailcom", 
				"9874561230", "BRB123456789", "",dependentsList );
		String expectedMsg = "Email Id must contain '@' and '.'";
		Exception exception = assertThrows(InvalidDataException.class, ()->{
			memberProfileSvc.addMemberProfile(profile);
		});
		
		assertTrue(expectedMsg.contains(exception.getMessage()));
	
	}
	
	@Test
	void addProfile_whenInvalidPhnNumLength_exceptionShouldBeThrown() {
		
		Dependent dependent1 = new Dependent("", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				21, "123 Max Street", "Houston", "Texas",
				"United States", "ricky@gmail.com", 
				"987456123", "BRB123456789", "",dependentsList );
		String expectedMsg = "Phone number must be in 10 digits";
		Exception exception = assertThrows(InvalidDataException.class, ()->{
			memberProfileSvc.addMemberProfile(profile);
		});
		
		assertTrue(expectedMsg.contains(exception.getMessage()));
	
	}
	
	@Test
	void addProfile_whenInvalidPhnNum_exceptionShouldBeThrown() {
		
		Dependent dependent1 = new Dependent("", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				21, "123 Max Street", "Houston", "Texas",
				"United States", "ricky@gmail.com", 
				"98745612AB", "BRB123456789", "",dependentsList );
		String expectedMsg = "Phone number must be in 10 digits";
		Exception exception = assertThrows(InvalidDataException.class, ()->{
			memberProfileSvc.addMemberProfile(profile);
		});
		
		assertTrue(expectedMsg.contains(exception.getMessage()));
	
	}
	
	@Test
	void addProfile_whenInvalidPanCrdLength_exceptionShouldBeThrown() {
		
		Dependent dependent1 = new Dependent("", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				21, "123 Max Street", "Houston", "Texas",
				"United States", "ricky@gmail.com", 
				"9874561230", "BRB12345", "",dependentsList );
		String expectedMsg = "Pan card number must be in 12 characters";
		Exception exception = assertThrows(InvalidDataException.class, ()->{
			memberProfileSvc.addMemberProfile(profile);
		});
		
		assertTrue(expectedMsg.contains(exception.getMessage()));
	
	}
	
	@Test
	void addProfile_whenInvalidPanCrd_exceptionShouldBeThrown() {
		
		Dependent dependent1 = new Dependent("", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				21, "123 Max Street", "Houston", "Texas",
				"United States", "ricky@gmail.com", 
				"9874561230", "BRB12345 789", "",dependentsList );
		String expectedMsg = "Pan card number must contain only alphabets and numbers (no spaces and symbols)";
		Exception exception = assertThrows(InvalidDataException.class, ()->{
			memberProfileSvc.addMemberProfile(profile);
		});
		
		assertTrue(expectedMsg.contains(exception.getMessage()));
	
	}
	
	@Test	
	void updateProfile_whenValidData_thenShouldBeUpdated() {
		
		Dependent dependent1 = new Dependent("R-234", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("R-456", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				21, "123 East Street", "New York", "New York",
				"United States", "ricky@gmail.com", 
				"9638527410", "BRB987456123", "R-123",dependentsList );
		Mockito.when(repository.save(profile)).thenReturn(profile);
		MemberProfile updated = null;
		String panCardNum = "BRB987456123";
		String contactNum = "9638527410";
		
		try {
			 updated = memberProfileSvc.updateMemberProfile(profile);
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(updated.getPanCrdNum(), panCardNum);
		assertEquals(updated.getContactNum(),contactNum);
		
	}
	
	@Test
	void updateProfile_whenInvalidAge_thenExceptionShouldBeThrown() {
		
		Dependent dependent1 = new Dependent("R-234", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("R-456", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				16, "123 East Street", "New York", "New York",
				"United States", "ricky@gmail.com", 
				"9638527410", "BRB987456123", "R-123",dependentsList );
		String expectedMsg = "Age must be greater than 18";
		
		Exception exception = assertThrows(InvalidDataException.class, ()->{
			memberProfileSvc.updateMemberProfile(profile);
		});
		
		assertTrue(expectedMsg.contains(exception.getMessage()));
		
		
	}
	
	@Test
	void updateProfile_whenInvalidEmail_thenExceptionShouldBeThrown() {
		
		Dependent dependent1 = new Dependent("R-234", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("R-456", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				19, "123 East Street", "New York", "New York",
				"United States", "rickygmail.com", 
				"9638527410", "BRB987456123", "R-123",dependentsList );
		String expectedMsg = "Email Id must contain '@' and '.'";
		
		Exception exception = assertThrows(InvalidDataException.class, ()->{
			memberProfileSvc.updateMemberProfile(profile);
		});
		
		assertTrue(expectedMsg.contains(exception.getMessage()));
		
		
	}
	
	@Test
	void updateProfile_whenEmailIdWithoutDot_thenExceptionShouldBeThrown() {
		
		Dependent dependent1 = new Dependent("R-234", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("R-456", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				19, "123 East Street", "New York", "New York",
				"United States", "ricky@gmailcom", 
				"9638527410", "BRB987456123", "R-123",dependentsList );
		String expectedMsg = "Email Id must contain '@' and '.'";
		
		Exception exception = assertThrows(InvalidDataException.class, ()->{
			memberProfileSvc.updateMemberProfile(profile);
		});
		
		assertTrue(expectedMsg.contains(exception.getMessage()));
		
		
	}

	@Test
	void updateProfile_whenInvalidPhnNumLength_thenExceptionShouldBeThrown() {
		
		Dependent dependent1 = new Dependent("R-234", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("R-456", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				19, "123 East Street", "New York", "New York",
				"United States", "ricky@gmail.com", 
				"96385", "BRB987456123", "R-123",dependentsList );
		String expectedMsg = "Phone number must be in 10 digits";
		
		Exception exception = assertThrows(InvalidDataException.class, ()->{
			memberProfileSvc.updateMemberProfile(profile);
		});
		
		assertTrue(expectedMsg.contains(exception.getMessage()));
	}

	@Test
	void updateProfile_whenInvalidPhnNum_thenExceptionShouldBeThrown() {
		
		Dependent dependent1 = new Dependent("R-234", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("R-456", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				19, "123 East Street", "New York", "New York",
				"United States", "ricky@gmail.com", 
				"9638527AF%", "BRB987456123", "R-123",dependentsList );
		String expectedMsg = "Phone number must be in 10 digits";
		
		Exception exception = assertThrows(InvalidDataException.class, ()->{
			memberProfileSvc.updateMemberProfile(profile);
		});
		
		assertTrue(expectedMsg.contains(exception.getMessage()));
	}
	
	@Test
	void updateProfile_whenInvalidPanCrdLength_thenExceptionShouldBeThrown() {
		
		Dependent dependent1 = new Dependent("R-234", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("R-456", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				19, "123 East Street", "New York", "New York",
				"United States", "ricky@gmail.com", 
				"9638527410", "BRB45689", "R-123",dependentsList );
		String expectedMsg = "Pan card number must be in 12 characters";
		
		Exception exception = assertThrows(InvalidDataException.class, ()->{
			memberProfileSvc.updateMemberProfile(profile);
		});
		
		assertTrue(expectedMsg.contains(exception.getMessage()));
	}
	
	@Test
	void updateProfile_whenInvalidPanCrdNum_thenExceptionShouldBeThrown() {
		
		Dependent dependent1 = new Dependent("R-234", "Morty", "Jerry", "2000-05-02");
		Dependent dependent2 = new Dependent("R-456", "Jerry", "Mathew", "1995-05-02");
		List<Dependent> dependentsList = new ArrayList<Dependent>();
		
		dependentsList.add(dependent1);
		dependentsList.add(dependent2);
		MemberProfile profile = new MemberProfile
				("Rick", "Sanchez", "1996-12-04",
				19, "123 East Street", "New York", "New York",
				"United States", "ricky@gmail.com", 
				"9638527410", "BRB1234*5678", "R-123",dependentsList );
		String expectedMsg = "Pan card number must contain only alphabets and numbers (no spaces and symbols)";
		
		Exception exception = assertThrows(InvalidDataException.class, ()->{
			memberProfileSvc.updateMemberProfile(profile);
		});
		
		assertTrue(expectedMsg.contains(exception.getMessage()));
	}
	
	@Test	
	void getProfile_whenValidEmailId_thenProfileShouldBeFound()  {	
		String memberId = "R-123", email ="ricky@gmail.com";
		
		MemberProfile found=null;
		try {
			found = memberProfileSvc.getMemberProfile(email);
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(found.getEmail(),email);
		assertEquals(found.getMemberId(), memberId);
		
		
	}
	
	@Test
	void getProfile_whenInvalidEmailId_thenExceptionShouldBeThrown() {
		String email = "invalidEmail@gmail.com";
		String expectedMsg = "Email not found";
		Exception exception = assertThrows(InvalidDataException.class, ()->{
			memberProfileSvc.getMemberProfile(email);
		});
		
		assertTrue(expectedMsg.contains(exception.getMessage()));
		
	}
	
	

}
