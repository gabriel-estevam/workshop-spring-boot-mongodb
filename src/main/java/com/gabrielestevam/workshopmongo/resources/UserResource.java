package com.gabrielestevam.workshopmongo.resources; //Camada de controladores REST que "conversa" com a camada service

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	//anotação indica que esse metodo sera um endpoint, para inserção utiliza-se o metodo POST
	@RequestMapping(method=RequestMethod.POST) 
	public ResponseEntity <Void> insert(@RequestBody UserDTO objDto) //anotação para que o parametro
	//consigo pegar os valores no body da requisição
	{
		//metodo para inserir um novo usuário, é do tipo void e tem como parametro objeto do tipo UserDTO
		
		/*Instanciado um objeto do tipo User, esse objeto recebe UserDTO ja convertido
		 * em User, conversão realizada atraves do metodo fromDTO da camada de serviço
		 * depois o objeto faça a inserção atraves do metodo insert da camada de serviço*/
		User obj = service.fromDTO(objDto); //objeto do tipo User que recebe o UserDTO ja convertido em User
		obj = service.insert(obj); //chama o metodo que faz a inserção 
		
		/*Abaixo estamos criando um header que retorna o id gerado apos a inserção, para 
		 * isso foi instanciado um objto do tipo URI que vai ser responsavel por pegar o id
		 * do novo objeto inserido*/
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); //retorna o metodo created, esse metodo retorna o codigo 201
		//que é de "recurso criado", esse metodo recebe o objeto URI
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE) //anotação que indica que esse metodo é um endpoint
	//o RequestMethod sera o DELETE
	public ResponseEntity <Void> delete(@PathVariable String id) 
	{
		//metodo para deletar um usuário pelo seu id, esse metodo retorna void, e o service sera 
		//responsavel por chamar o metodo delete
		service.delete(id); //deleta o usuário, recebe o id do parametro do metodo
		return ResponseEntity.noContent().build(); //Na resposta foi usado o metodo noContent(), esse metodo
		//retorna o status "204 no content"
	}
	
	//anotação tem dois parametros, primeiro o local onde ela vai pegar o id e o RequestMethod que na atualização é o PUT
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT) 
	public ResponseEntity <Void> update(@RequestBody UserDTO objDto, @PathVariable String id) 
	{
		/*metodo para atualizar um usuário, tem como parametro:
		UserDTO - objeto com anotação RequestBody, esse objeto tem os dados do corpo da requisição (arquivo .json)
		Strind id - objeto com anotação PathVariable, esse objeto pega reconhece o id que foi informado na URL após o /id
		*/
		//as linhas abaixo realiza a atulização do objeto
		
		User obj = service.fromDTO(objDto); //instanciado um objeto do tipo User, esse objeto recebe o objeto DTO ja convertido em User
		obj.setId(id); //depois que ele recebe o objeto da requisição, seta seu id 
		obj = service.update(obj); //atualiza os dados e passa para o objeto os novos dados			
		return ResponseEntity.noContent().build(); //na resposta chama o metodo noContent, não mostra nada na requisição
		//apenas o status 204 no content indicando a atualização.
	}
}