package com.profileService.service;

import java.util.NoSuchElementException;

import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profileService.entity.MemberProfile;
import com.profileService.error.InvalidDataException;
import com.profileService.repository.MemberProfileRepository;



@Service
public class MemberProfileServiceImpl implements MemberProfileService {
	@Autowired
	private MemberProfileRepository repository;
	
	private Logger logger = LoggerFactory.getLogger(MemberProfileServiceImpl.class);
	
	private static Random random=new Random();
	
	
	public MemberProfile addMemberProfile(MemberProfile profile) throws InvalidDataException {
		logger.info("Inside service to add profile of user" + profile.getEmail());
	validateInput(profile);
	
		 
		
		 
		 generateMemberId(profile);	
		 
		return repository.insert(profile);
	}
	
	public MemberProfile updateMemberProfile(MemberProfile profile) throws InvalidDataException {
		logger.info("Inside service to update profile of user" + profile.getEmail());
		validateInput(profile);
		
		MemberProfile savedProfile = getMemberProfile(profile.getEmail());
		
		
			savedProfile.setDob(profile.getDob());		
		
			savedProfile.setAge(profile.getAge());
			
			savedProfile.setAddress(profile.getAddress());
			
			savedProfile.setCountry(profile.getCountry());

			savedProfile.setState(profile.getState());

			savedProfile.setDistrict(profile.getDistrict());

			savedProfile.setContactNum(profile.getContactNum());

			savedProfile.setPanCrdNum(profile.getPanCrdNum());

			savedProfile.getDependents().get(0).setDob(profile.getDependents().get(0).getDob());

			savedProfile.getDependents().get(1).setDob(profile.getDependents().get(1).getDob());
		
		
		return repository.save(savedProfile);
		
	}
	
	public MemberProfile getMemberProfile(String email) throws InvalidDataException{
		MemberProfile profile = null;
		try {
			logger.info("Lookup of email " + email + "in db");
				profile = repository.findById(email).get();
		}
		catch (NoSuchElementException e) {
			logger.error("Lookup of email " + email + " failed");
			throw new InvalidDataException("Email not found");
		}
		logger.info("Successful lookup of email " + email + "in db");
				return profile;
	}
	private void validateInput(MemberProfile profile) throws InvalidDataException {
		

		
		String contactNum = profile.getContactNum();
	String panCrdNum = profile.getPanCrdNum();
	String email =  profile.getEmail();
		String phoneRegex= "[0-9]+";
		String emailRegex="^(.+)@(.+)\\.(.+)$";
		
		
		Integer age  = Optional.of(profile.getAge()).orElse(0);		
		 if(age<18) {
			 throw new InvalidDataException("Age must be greater than 18");
		 }
		
		 if (email.length() > 0 && !email.matches(emailRegex)) {
			 
			 throw new InvalidDataException("Email Id must contain '@' and '.'");
		 }
		
	 if (contactNum.length()>0 && (contactNum.length() !=10 || !contactNum.matches(phoneRegex))) {
		 
		 throw new InvalidDataException("Phone number must be in 10 digits");
	 }
	 
	 if (panCrdNum.length() > 0  && ( panCrdNum.length()!=12)) {
		 throw new InvalidDataException("Pan card number must be in 12 characters");
	 }
	  
	 if (!panCrdNum.matches("^[a-zA-Z0-9]*$")) {
		 throw new InvalidDataException("Pan card number must contain only alphabets and numbers (no spaces and symbols)");
	 }
	 
	
	 
	}
	
	private void generateMemberId(MemberProfile profile) {
		
		String dependent2FirstName=profile.getDependents().get(1).getFirstName();
		profile.setMemberId("R-" + generateRandomNumber());
		profile.getDependents().get(0).setMemberId("R-" + generateRandomNumber());
		if(dependent2FirstName.length() != 0  )
			profile.getDependents().get(1).setMemberId("R-" + generateRandomNumber());
		//random = null;
		

	}
	
//	public boolean validateCustomerName(Member member) {
//		int noOfDependent = member.getDependents().size();
//		String dependent1FirstName = member.getDependents().get(0).getFirstName();
//		String dependent1LarstName = member.getDependents().get(0).getLastName();
//		
//		Optional.ofNullable(value)
//		return false;
//		
//	}
	
	private String generateRandomNumber() {
		
		
		int randomNum = random.nextInt(1000);
		
		if(randomNum<10) {
			return ("00"+randomNum);
		}
		if (randomNum >=10 && randomNum < 100) {
			return ("0"+randomNum);
		}
		return Integer.toString(randomNum);
		
	}


	
//	private void generateClaimNumber() {
//		
//	}
	
}
