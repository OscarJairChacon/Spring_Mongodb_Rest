package com.cert.prueba.services;


import java.util.List;


//import com.blog.samples.domain.Persona;
import com.cert.prueba.domain.Personas;

public interface PersonaService {

	public Personas getPersona(Long id);
	public Personas getPersonaByNombres(String name);
	public Personas getPersonaById(String personaId_p);
	public List<Personas> getAllPersonas();
	public Personas deletePersona(String personaId);
	public Personas createPersona(Personas pers);
	public Personas updatePersonas(Personas pers);
}
