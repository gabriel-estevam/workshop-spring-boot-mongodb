package com.gabrielestevam.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielestevam.workshopmongo.domain.Post;
import com.gabrielestevam.workshopmongo.domain.User;
import com.gabrielestevam.workshopmongo.dto.AuthorDTO;
import com.gabrielestevam.workshopmongo.dto.CommentDTO;
import com.gabrielestevam.workshopmongo.repository.PostRepository;
import com.gabrielestevam.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner 
{
	/*Classe de configuração - operação de instanciação da base de dados
	 * sempre que executarmos esssa aplicação, vamos instanciar os dados
	 * e salva no banco de dados os objetos que queremos
	 * Essa classe implementa o interface CommandLineRunner
	*/
	//Essa classe tem dependencia com o UserRepository pois vamos fazer operação com o banco de dados
	@Autowired
	private UserRepository userRepository;
	
	//essa classe tem dependencia com o PostRepository, para realizar acesso a dados
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception 
	{
	//implementação da interface CommandLineRunner (contrato).
	//Nesse metodo vamos instanciar os objetos e salvar no banco de dados
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //formatando a data para dia/mes/ano
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); //timezone padrã GMT
		
		//abaixo limpamos a coleção no banco de dados, isto a primeira coisa a ser feito ao iniciar a aplicação é deletar os dados existente
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		//objetos a ser salvo no banco de dados ao iniciar a aplicação
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob)); //REFATORADO - primeiro vamos salvar os usuários para gerar o id e depois vamos
		//passsar para o authorDTO

		//REFATORADO - agora no post instanciamos um AuthorDTO passando como argumento um obj User
		Post post1 = new Post(null,sdf.parse("21/03/2021") , "Partiu viagem","Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null,sdf.parse("23/03/2021") , "Bom dia","Acordei feliz hoje!", new AuthorDTO(maria));
		Post post3 = new Post(null,sdf.parse("20/02/2021") , "Partiu academia","Bora fazer aquele treino completo!", new AuthorDTO(alex));

		//REFATORADO - insntanciado comentarios realizado nos posts
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2021"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2021"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um otimo dia!", sdf.parse("23/03/2021"), new AuthorDTO(alex));
		CommentDTO c4 = new CommentDTO("Boa treino mano!", sdf.parse("21/03/2021"), new AuthorDTO(bob));

		//post1 e post2 recebem os comentarios
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		post3.getComments().addAll(Arrays.asList(c4));

		//Abaixo salvamos os posts (documents) no banco de dados
		postRepository.saveAll(Arrays.asList(post1,post2,post3));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		alex.getPosts().addAll(Arrays.asList(post3));
		userRepository.save(maria);
		userRepository.save(alex);
	}

}
