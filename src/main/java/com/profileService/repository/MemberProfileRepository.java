package com.profileService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.profileService.entity.MemberProfile;



@Repository
public interface MemberProfileRepository extends MongoRepository<MemberProfile, String> {
	
	

}
