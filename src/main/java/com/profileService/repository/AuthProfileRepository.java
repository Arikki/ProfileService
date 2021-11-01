package com.profileService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.profileService.entity.AuthRequest;



@Repository
public interface AuthProfileRepository extends MongoRepository<AuthRequest, String>{
//
//	@Query(value="{ 'email' : ?0 }", fields="{ 'firstName' : 1, 'lastName' : 1}")
//	AuthRequest findByEmail(String email);
}
