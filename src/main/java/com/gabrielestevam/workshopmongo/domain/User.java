package com.gabrielestevam.workshopmongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
	
	//anotação indicando que o atributo esta referenciando UMA COLEÇÃO NO MONGODB
	//parametro lazy = true garante que NÃO vai trazer os dados dos usuários quando fizemos uma CONSULTA no usuários
	//com isso evitamos de carregar muitos dados sem utilidade na rede
	@DBRef(lazy = true)
	private List<Post> posts = new ArrayList<>(); /*REFATORADO - agora os usuários tem agregado a ele os posts dele
	tendo a referencia deles não sendo de forma aninhado conforme design definido,
	portanto como usuário pode haver n posts então os post serão uma lista */
	
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
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
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
