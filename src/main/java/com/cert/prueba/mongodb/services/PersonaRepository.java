package com.cert.prueba.mongodb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.cert.prueba.domain.Personas;
import com.cert.prueba.services.PersonaService;

	@Service
	public class PersonaRepository implements PersonaService {

		@Autowired
		MongoTemplate mongoTemplate;

		public static final String PERSONA_COLLECTION = "Personas";

		public Personas getPersona(Long id){
			return mongoTemplate.findById(id, Personas.class,PERSONA_COLLECTION);
		}

		public Personas getPersonaByNombres(String name){
			List<Personas> personaList = mongoTemplate.find(new Query(Criteria.where("PersonaNombres").
					is(name)), Personas.class,PERSONA_COLLECTION);
			return personaList!=null && personaList.size()>0?personaList.get(0):null;
		}
		
		public Personas getPersonaById(String personaId_p) {
			List<Personas> personaList = mongoTemplate.find(new Query(Criteria.where("PersonaId").
					is(personaId_p)), Personas.class,PERSONA_COLLECTION);
			return personaList!=null && personaList.size()>0?personaList.get(0):null;
		}

		public List<Personas> getAllPersonas() {
			return mongoTemplate.findAll(Personas.class,PERSONA_COLLECTION);
		}

		public Personas deletePersona(String personaId_p) {
			Personas persona = getPersonaById(personaId_p);
			mongoTemplate.remove(persona);
			return persona;
		}

		public Personas createPersona(Personas pers) {
			Personas persona = new Personas();
			List<Personas> personaList = new ArrayList<Personas>();
			int val =1;
			personaList = getAllPersonas();
			val = personaList.size();
			persona.setId(val+1);
			mongoTemplate.save(persona, PERSONA_COLLECTION);
			return persona;
			
		}

		public Personas updatePersonas(Personas pers) {
			Update update = new Update();
			update.set("nombres", PERSONA_COLLECTION);
			mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(pers)), update,PERSONA_COLLECTION);
			return pers;
		}
	}
