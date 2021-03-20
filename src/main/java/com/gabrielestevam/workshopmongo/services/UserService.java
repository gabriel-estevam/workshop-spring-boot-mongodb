package com.gabrielestevam.workshopmongo.services; //camada de serviço que conversa 
//com a camada repository e vai ser o serviço que trabalha com os usuários

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielestevam.workshopmongo.domain.User;
import com.gabrielestevam.workshopmongo.repository.UserRepository;
import com.gabrielestevam.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired // anotação dizendo que essa classe tem uma dependencia, nesse caso com o UserRepository da camada de repositorio
	private UserRepository repo; //atributo para acessar o repositório do User (UserRepository)
	
	public List<User> findAll() {
	//metodo que vai retorna todos os usuários do banco
		return repo.findAll(); //retorna todos os usuário
		//esse findAll() é do spring.data
	}
	
	public User findById(String id) {
	/*metodo para retorna um usuário pelo seu id
	 * Declarado uma objeto User usando o "Optional", Optional -  encapsula o retorno de 
	 * alguma metodo, validando se é nulo ou não pondendo tratar o null pointer exception.
	 * Esse objeto recebe o repositorio "rep" que chama o meotodo findById(informado o id
	 * do metodo).
	 * no retorno usamos o metodo orElseThrow, pois usamos o "optional", esse metodo
	 * lança a exceção ObjectNotFoundException
	*/
		Optional <User> obj = repo.findById(id); //objeto recebe a chamada do metodo findById do Repository
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não encontrado")); //retorna o objeto se encontra,
		//se não lança a exceção personalizada
	}
}
