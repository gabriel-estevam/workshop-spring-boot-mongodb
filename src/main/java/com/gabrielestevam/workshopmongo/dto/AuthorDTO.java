package com.gabrielestevam.workshopmongo.dto;

import java.io.Serializable;

import com.gabrielestevam.workshopmongo.domain.User;

//Classe que representa o autor (usuário) do post 
public class AuthorDTO implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	
	public AuthorDTO() {
	}
	
	public AuthorDTO(User obj) {
	//nesse construtor estamos aplicando o DTO, que tem como parametro o usuário
	//e do usuários vamos precisar do id e name
		id = obj.getId(); //recebe o id do usuário
		name = obj.getName(); //recebe o nome do usuário
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
	
}
