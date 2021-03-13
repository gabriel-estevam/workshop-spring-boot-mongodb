package com.gabrielestevam.workshopmongo.resources; //pacote dos controladores Rest da aplicação

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielestevam.workshopmongo.domain.User;

@RestController //anotação que diz que essa classe é um controlador REST
@RequestMapping(value="/users") //anotação que indica onde vai retorna os objetos .json na URL do navegador
public class UserResource 
{
	@RequestMapping(method=RequestMethod.GET) //anotação que indica que esse metodo sera um endpoint (poderia ser o GetMapping tambem)
	public ResponseEntity <List<User>> findAll() 
	{
		//metodo para retorna todos os usários.
		//Por enquanto estamos usando dados mocados
		User maria = new User("1", "Maria Brown", "maria@gmail.com");
		User alex = new User("2", "Alex Green", "alex@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria,alex));
		return ResponseEntity.ok().body(list);
	}
}
