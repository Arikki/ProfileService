package com.profileService.service;

import com.profileService.entity.MemberProfile;
import com.profileService.error.InvalidDataException;

public interface MemberProfileService {
	
	public MemberProfile addMemberProfile(MemberProfile profile) throws InvalidDataException;
	public MemberProfile updateMemberProfile (MemberProfile profile) throws InvalidDataException;
	public MemberProfile getMemberProfile(String email) throws InvalidDataException;
	
}
