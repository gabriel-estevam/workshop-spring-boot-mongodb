package com.gabrielestevam.workshopmongo.services; //camada de serviço que conversa 
//com a camada repository e vai ser o serviço que trabalha com os usuários

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielestevam.workshopmongo.domain.User;
import com.gabrielestevam.workshopmongo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired // anotação dizendo que essa classe tem uma dependencia, nesse caso com o UserRepository da camada de repositorio
	private UserRepository repo; //atributo para acessar o repositório do User (UserRepository)
	
	public List<User> findAll() {
	//metodo que vai retorna todos os usuários do banco
		return repo.findAll(); //retorna todos os usuário
		//esse findAll() é do spring.data
	}
}
