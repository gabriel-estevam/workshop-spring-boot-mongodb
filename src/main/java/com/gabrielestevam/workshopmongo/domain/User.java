package com.gabrielestevam.workshopmongo.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//essa anotação indica que essa classe coresponde a uma coleção lá no mongoDB, o atributo collection indica o nome da coleção criada la na base de dados
@Document(collection = "user")
public class User implements Serializable
{
	//Entidade do Dominio Usuário
	
	private static final long serialVersionUID = 1L;
	
	//anotação indicando o id da classe
	@Id
	private String id;
	
	private String name;
	private String email;
	
	public User() {
	}

	public User(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
