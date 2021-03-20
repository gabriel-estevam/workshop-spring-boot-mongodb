package com.gabrielestevam.workshopmongo.dto;

import java.io.Serializable;
import com.gabrielestevam.workshopmongo.domain.User;
/*package do padrão DTO
 * DTO (Data Transfer Object): é um objeto que tem o papel de carregar
 * dados da entidade de forma simples, podendo "projetar" apenas alguns
 * dados da entidade original, por exemplo caso um entidade tem
 * trinta atributos, mas queremos retorna (fazer uma lista) de três
 * então pegamos so o que precisamos sem precisar trafegar pela rede
 * os demais dados, com esse padrão conseguimos:
 * - Otimizar o tráfego (trafegando menos dados);
 * - Evitar que dados de interesse exclusivo do sistema fiquem expostos
 * (senhas, dadod de auditoria, data de criação e atulização do objeto,etc);
 * - Customizar os objetos trafegados conforme a necessidade de cada requisição,
 * exemplo: "para alterar um produto, você precisa dos dados A, B e C; ja para listar
 * os produtos, precisa dos dados A e B e a categoria de cada produto, etc). */

public class UserDTO implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	
	public UserDTO() {	
	}
	
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
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
	
}
