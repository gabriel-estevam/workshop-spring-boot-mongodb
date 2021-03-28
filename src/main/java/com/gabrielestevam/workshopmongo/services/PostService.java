package com.gabrielestevam.workshopmongo.services; //camada de serviço que conversa 
//com a camada repository e vai ser o serviço que trabalha com os usuários

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielestevam.workshopmongo.domain.Post;
import com.gabrielestevam.workshopmongo.repository.PostRepository;
import com.gabrielestevam.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired // anotação dizendo que essa classe tem uma dependencia, nesse caso com o PostRepository da camada de repositorio
	private PostRepository repo; //atributo para acessar o repositório do Post
	
	public Post findById(String id) {
		Optional <Post> obj = repo.findById(id); //objeto recebe a chamada do metodo findById do Repository
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não encontrado")); //retorna o objeto se encontra,
		//se não lança a exceção personalizada
	}
	
	public List<Post> findByTitle(String text) {
	//metodo para retornar titulo, tem como parametro uma String "text"
		return repo.searchTitle(text); //REFATORADO - agora estamos chamando o metodo personalizado searchTitle
	   //retornamos o metodo do Repositoy passando como parametro a String do metodo
	}
}
