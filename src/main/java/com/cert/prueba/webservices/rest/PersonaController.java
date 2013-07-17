package com.cert.prueba.webservices.rest;

import java.util.List;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import com.cert.prueba.domain.Personas;
import com.cert.prueba.services.PersonaService;

@Controller
public class PersonaController {

	@Autowired
	private PersonaService personaService_i;

	@Autowired
	private View jsonView_i;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";
	private static final Logger logger_c = Logger.getLogger(PersonaController.class);
	
	@RequestMapping(value = "/rest/personas/{personaId}", method = RequestMethod.GET)
	public ModelAndView getPersonas(@PathVariable("personaId") String personaId_p) {
		Personas persona = null;

		if (isEmpty(personaId_p) || personaId_p.length() < 5) {
			String sMessage = "Error al invocar el metodo getPersona - Parametro Id invalido";
			return createErrorResponse(sMessage);
		}

		try {
			persona = personaService_i.getPersonaById(personaId_p);
		} catch (Exception e) {
			String sMessage = "Error al invocar getPersona. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		logger_c.debug("Retorna Persona: " + persona.toString());
		return new ModelAndView(jsonView_i, DATA_FIELD, persona);
	}

	
	@RequestMapping(value = "/rest/personas/", method = RequestMethod.GET)
	public ModelAndView getPersonas() {
		List<Personas> personas = null;

		try {
			personas = personaService_i.getAllPersonas();
		} catch (Exception e) {
			String sMessage = "Error al capturar persona. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		logger_c.debug("Retorna personas: " + personas.toString());
		return new ModelAndView(jsonView_i, DATA_FIELD, personas);
	}

	
	@RequestMapping(value = { "/rest/personas/" }, method = { RequestMethod.POST })
	public ModelAndView createPersonas(@RequestBody Personas persona_p,
			HttpServletResponse httpResponse_p, WebRequest request_p) {

		Personas createdPersonas;
		logger_c.debug("Crea Personas: " + persona_p.toString());

		try {
			createdPersonas = personaService_i.createPersona(persona_p);
	
		} catch (Exception e) {
			String sMessage = "Error al crear nueva persona. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		/* set HTTP response */
		httpResponse_p.setStatus(HttpStatus.CREATED.value());

		/* set localizacion del recurso*/
		httpResponse_p.setHeader("Location", request_p.getContextPath() + "/rest/personas/" + persona_p.getId());

		return new ModelAndView(jsonView_i, DATA_FIELD, createdPersonas);
	}

	
	@RequestMapping(value = { "/rest/personas/{personaId}" }, method = { RequestMethod.PUT })
	public ModelAndView updatePersonas(@RequestBody Personas persona_p, @PathVariable("personaId") String personaId_p,
								   HttpServletResponse httpResponse_p) {

		logger_c.debug("Actualiza Personas: " + persona_p.toString());

		/* validar id de persona*/
		if (isEmpty(personaId_p) || personaId_p.length() < 5)	{
			String sMessage = "Error al actualizar persona";
			return createErrorResponse(sMessage);
		}

		Personas persona = null;

		try {
			persona= personaService_i.updatePersonas(persona_p);
		} catch (Exception e) {
			String sMessage = "Error actualizar persona. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		httpResponse_p.setStatus(HttpStatus.OK.value());
		return new ModelAndView(jsonView_i, DATA_FIELD, persona);
	}

	
	@RequestMapping(value = "/rest/personas/{personaId}", method = RequestMethod.DELETE)
	public ModelAndView removePersonas(@PathVariable("personaId") String personaId_p,
								   HttpServletResponse httpResponse_p) {

		logger_c.debug("Deleting Personas Id: " + personaId_p.toString());

		/* valida persona Id */
		if (isEmpty(personaId_p) || personaId_p.length() < 5) {
			String sMessage = "Error al borrar la persona - Id no valido";
			return createErrorResponse(sMessage);
		}

		try {
			personaService_i.deletePersona(personaId_p);
		} catch (Exception e) {
			String sMessage = "Error al invocar getPersonas. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		httpResponse_p.setStatus(HttpStatus.OK.value());
		return new ModelAndView(jsonView_i, DATA_FIELD, null);
	}

	public static boolean isEmpty(String s_p) {
		return (null == s_p) || s_p.trim().length() == 0;
	}

	/**
	 * Crea error REST response.
	 *
	 */
	private ModelAndView createErrorResponse(String sMessage) {
		return new ModelAndView(jsonView_i, ERROR_FIELD, sMessage);
	}

	
	public void setPersonasService(PersonaService personaService_p) {
		personaService_i = personaService_p;
	}

	
	public void setJsonView(View view) {
		jsonView_i = view;
	}

}