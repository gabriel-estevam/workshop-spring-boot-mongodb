package com.gabrielestevam.workshopmongo.resources; //Camada de controladores REST que "conversa" com a camada service

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielestevam.workshopmongo.domain.User;
import com.gabrielestevam.workshopmongo.dto.UserDTO;
import com.gabrielestevam.workshopmongo.services.UserService;

@RestController //anotação que diz que essa classe é um controlador REST
@RequestMapping(value="/users") //anotação que indica onde vai retorna os objetos .json na URL do navegador
public class UserResource 
{
	//anotação dizendo que existe uma dependencia do UserService nessa classe
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET) //anotação que indica que esse metodo sera um endpoint (poderia ser o GetMapping tambem)
	public ResponseEntity <List<UserDTO>> findAll() 
	{
		//metodo para retorna todos os usários.
		//METODO REFATORADO - tipo da lista agora sera UserDTO
		
		List<User> list = service.findAll(); //lista do tipo User, recebe o metodo da camada de serviço
		/*Abaixo foi declarado uma nova lista para retorna um UserDTO, 
		 * essa lista recebe a lista "list" de usuário acima, porem vamos converter
		 * para o tipo DTO. Para isso usamos uma expressão lambda:
		 * stream - converte a lista original para stream, para ficar compativel para
		 * as expressões lambdas;
		 * map - metodo que pega cada objeto "x" da lista original "list" e vai 
		 * instanciar um UserDTO para "x", isto é cada User
		 * collect - para voltar a coleção (porque estamos no metodo map)
		 * Collectors.toList - volta a lista para o tipo "List" novamente*/
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto); //metodo retorna no corpo da requisição os usuários (objetos) do banco de dados
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //anotação que indica que esse metodo sera um endpoint (poderia ser o GetMapping tambem)
	public ResponseEntity <UserDTO> findById(@PathVariable String id) //anotação para pegar a String do parametro da URL  
	{
		//metodo para retorna um usuário pelo seu id retornando o DTO, esse id é inserido na URL do navegador
		User obj = service.findById(id); //instanciado um objeto do tipo usuário recebendo a chamada do metodo findById do service
		return ResponseEntity.ok().body(new UserDTO(obj));//a resposta é o objeto convertido para UserDTO
	}
}