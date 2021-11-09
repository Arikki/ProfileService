package com.profileService.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;


@DataMongoTest
@ExtendWith(SpringExtension.class)
class MemberProfileRepositoryTest {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Test
	void testInsertProfile() {
		List<DBObject> array = new ArrayList<DBObject>();
		
		DBObject dependent1 = BasicDBObjectBuilder.start()
							 .add("memberId", "R-234")
							 .add("firstName", "Morty")
							 .add("lastName", "Smith")
							 .add("dob", "04-11-1996")
							 .get();
		DBObject dependent2 = BasicDBObjectBuilder.start()
				 .add("memberId", "R-456")
				 .add("firstName", "Jade")
				 .add("lastName", "Smith")
				 .add("dob", "04-11-1996")
				 .get();
		array.add(dependent1);
		array.add(dependent2);
		DBObject objectToSave = BasicDBObjectBuilder.start()
	            .add("firstName", "Rick")
	            .add("lastName", "Sanchez")
	            .add("dob", "04-11-1996")
	            .add("_id", "xyz@gmail.com")
	            .add("age", "25")
	            .add("address", "Texas")
	            .add("state", "Texas")
	            .add("district", "Houston")
	            .add("state", "USA")
	            .add("contactNum", "9874461230")
	            .add("panCrdNum", "BRB123456789")
	            .add("memberId", "R-123")
	            .add("dependents", array)
	            .get();
		
		DBObject insertedObj = mongoTemplate.insert(objectToSave, "memberProfile");
		
		assertEquals("xyz@gmail.com",insertedObj.get("_id"));
		
	}
	
	@Test
	void testProfileUpdate() {
		
	List<DBObject> array = new ArrayList<DBObject>();
		
		DBObject dependent1 = BasicDBObjectBuilder.start()
							 .add("memberId", "R-234")
							 .add("firstName", "Morty")
							 .add("lastName", "Smith")
							 .add("dob", "04-11-1996")
							 .get();
		DBObject dependent2 = BasicDBObjectBuilder.start()
				 .add("memberId", "R-456")
				 .add("firstName", "Jade")
				 .add("lastName", "Smith")
				 .add("dob", "04-11-1996")
				 .get();
		array.add(dependent1);
		array.add(dependent2);
		DBObject objectToSave = BasicDBObjectBuilder.start()
	            .add("firstName", "Rick")
	            .add("lastName", "Sanchez")
	            .add("dob", "04-11-1996")
	            .add("_id", "abc@gmail.com")
	            .add("age", "25")
	            .add("address", "Texas")
	            .add("state", "Texas")
	            .add("district", "Houston")
	            .add("state", "USA")
	            .add("contactNum", "9874461230")
	            .add("panCrdNum", "BRB123456789")
	            .add("memberId", "R-123")
	            .add("dependents", array)
	            .get();
		
		DBObject insertedObj = mongoTemplate.insert(objectToSave, "memberProfile");
		
		assertEquals("9874461230",insertedObj.get("contactNum"));
		
		DBObject objectToUpdate = BasicDBObjectBuilder.start()
	            .add("firstName", "Rick")
	            .add("lastName", "Sanchez")
	            .add("dob", "04-11-1996")
	            .add("_id", "abc@gmail.com")
	            .add("age", "25")
	            .add("address", "Texas")
	            .add("state", "Texas")
	            .add("district", "Houston")
	            .add("state", "USA")
	            .add("contactNum", "9638527410")
	            .add("panCrdNum", "BRB123456789")
	            .add("memberId", "R-123")
	            .add("dependents", array)
	            .get();
		
		DBObject updatedObj = mongoTemplate.save(objectToUpdate, "memberProfile");
		
		assertEquals("9638527410",updatedObj.get("contactNum"));
		
	}
	
	@Test
	void testGetProfile() {
		
	List<DBObject> array = new ArrayList<DBObject>();
		
		DBObject dependent1 = BasicDBObjectBuilder.start()
							 .add("memberId", "R-234")
							 .add("firstName", "Morty")
							 .add("lastName", "Smith")
							 .add("dob", "04-11-1996")
							 .get();
		DBObject dependent2 = BasicDBObjectBuilder.start()
				 .add("memberId", "R-456")
				 .add("firstName", "Jade")
				 .add("lastName", "Smith")
				 .add("dob", "04-11-1996")
				 .get();
		array.add(dependent1);
		array.add(dependent2);
		DBObject objectToSave = BasicDBObjectBuilder.start()
	            .add("firstName", "Rick")
	            .add("lastName", "Sanchez")
	            .add("dob", "04-11-1996")
	            .add("_id", "get@gmail.com")
	            .add("age", "25")
	            .add("address", "Texas")
	            .add("state", "Texas")
	            .add("district", "Houston")
	            .add("state", "USA")
	            .add("contactNum", "9874461230")
	            .add("panCrdNum", "BRB123456789")
	            .add("memberId", "R-123")
	            .add("dependents", array)
	            .get();
		
		 mongoTemplate.save(objectToSave, "memberProfile");
		DBObject foundObj = mongoTemplate.findById("get@gmail.com", DBObject.class, "memberProfile");
		
		assertEquals("get@gmail.com", foundObj.get("_id"));
		
		
	}

}
