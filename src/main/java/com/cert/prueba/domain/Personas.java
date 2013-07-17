package com.cert.prueba.domain;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "personas")
public class Personas implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombres;
	private String sexo;
	
	public Personas() {
		
	}
	
	public Personas(String nombres,String sexo){
		this.nombres= nombres;
		this.sexo=sexo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Personas [id=" + id + ", nombres=" + nombres + ", sexo=" + sexo
				+ "]";
	}

}
