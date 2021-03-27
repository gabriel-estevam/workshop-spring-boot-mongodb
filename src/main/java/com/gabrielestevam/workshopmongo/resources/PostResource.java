package com.gabrielestevam.workshopmongo.resources; //Camada de controladores REST que "conversa" com a camada service

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielestevam.workshopmongo.domain.Post;
import com.gabrielestevam.workshopmongo.resources.util.URL;
import com.gabrielestevam.workshopmongo.services.PostService;

@RestController //anotação que diz que essa classe é um controlador REST
@RequestMapping(value="/posts") //anotação que indica onde vai retorna os objetos .json na URL do navegador
public class PostResource 
{
	//anotação dizendo que existe uma dependencia do PostService nessa classe
	@Autowired
	private PostService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //anotação que indica que esse metodo sera um endpoint (poderia ser o GetMapping tambem)
	public ResponseEntity <Post> findById(@PathVariable String id) //anotação para pegar a String do parametro da URL  
	{
		//metodo para retorna um usuário pelo seu id retornando o Post, esse id é inserido na URL do navegador
		Post obj = service.findById(id); //instanciado um objeto do tipo Post recebendo a chamada do metodo findById do service
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET)//endpoint
	public ResponseEntity <List<Post>> findByTittle(@RequestParam(value="text", defaultValue="") String text)  
	{
		//RequestParam - anotação para pegar o criterio da busca na URL, value é o parametro que contem o criterio da busca, 
		//defaultValue - String vazia se não for informado um valor na URL
		
		//meotodo para retorna uma consulta, retorna uma lista de Post que contem o title informado na critério de busca
    
		text = URL.decodeParam(text); //pega o parametro e chama o metodo decodeParam para decodificar o texto
		List<Post> list = service.findByTitle(text); //instanciado uma lista de "post" que recebe o metodo findByTittle que é o metodo que faz a busca
		return ResponseEntity.ok().body(list); //retorna uma list de post contendo o texto informado
	}
		
}