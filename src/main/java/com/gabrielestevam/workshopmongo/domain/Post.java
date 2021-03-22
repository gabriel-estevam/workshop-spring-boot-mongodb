package com.gabrielestevam.workshopmongo.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//anotação para indicar ao MongoDB que essa classe é um document
//o parametro collection é para indicar o nome desse classe no MongoDB quando ele for criar a tabela
//esse parametro é opcional
@Document(collection = "post")
public class Post implements Serializable {
//classe que representa os Post do usuário, essa classe tem associado um auto que é o usuário
	
	private static final long serialVersionUID = 1L;
	
	//anotação para referenciar o id desse document
	@Id
	private String id;
	
	private Date date;
	private String title;
	private String body;

	private User author; //associção conforme diagrama do modelo de negócio
	
	public Post() {
	}

	public Post(String id, Date date, String title, String body, User author) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.body = body;
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public User getUser() {
		return author;
	}

	public void setUser(User author) {
		this.author = author;
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
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}