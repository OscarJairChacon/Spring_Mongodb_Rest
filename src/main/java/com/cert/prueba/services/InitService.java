package com.cert.prueba.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class InitService {

	@Autowired
	MongoTemplate mongoTemplate;

	
	@SuppressWarnings("unused")
	private void init() {

		mongoTemplate.getCollection("ejemplodb").drop();

		DBCollection personasCollection = mongoTemplate
				.getCollection("ejemplodb");

		BasicDBObject personaDoc = new BasicDBObject();

		personaDoc.put("_id", new Long(1));
		personaDoc.put("Nombres", 2);
		personaDoc.put("Sexo", 3);
		personasCollection.insert(personaDoc);
	}
}
