package com.profileService.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.profileService.entity.AuthRequest;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class AuthProfileRepositoryTest {

	@Autowired
	private MongoTemplate mongoTemplate;

	
	@Test
	void testFindById() {
		
		DBObject objectToSave = BasicDBObjectBuilder.start()
	            .add("firstName", "Rick")
	            .add("lastName", "Sanchez")
	            .add("dateOfBirth", "04-11-1996")
	            .add("_id", "xyz@gmail.com")
	            .add("password", "password")
	            .get();
		
		 mongoTemplate.save(objectToSave, "authDetails");
		 
		DBObject foundObj = mongoTemplate.findById("xyz@gmail.com", DBObject.class, "authDetails");
		assertEquals("Rick",foundObj.get("firstName"));

	}

}
