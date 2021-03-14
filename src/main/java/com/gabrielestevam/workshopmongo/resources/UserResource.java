package com.gabrielestevam.workshopmongo.resources; //Camada de controladores REST que "conversa" com a camada service

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielestevam.workshopmongo.domain.User;
import com.gabrielestevam.workshopmongo.services.UserService;

@RestController //anotação que diz que essa classe é um controlador REST
@RequestMapping(value="/users") //anotação que indica onde vai retorna os objetos .json na URL do navegador
public class UserResource 
{
	//anotação dizendo que existe uma dependencia do UserService nessa classe
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET) //anotação que indica que esse metodo sera um endpoint (poderia ser o GetMapping tambem)
	public ResponseEntity <List<User>> findAll() 
	{
		//metodo para retorna todos os usários.
		//METODO REFATORADO
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list); //metodo retorna no corpo da requisição os usuários (objetos) do banco de dados
	}
}
