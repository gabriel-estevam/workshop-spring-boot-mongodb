package com.gabrielestevam.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielestevam.workshopmongo.domain.Post;
import com.gabrielestevam.workshopmongo.domain.User;
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
		Post post1 = new Post(null,sdf.parse("21/03/2021") , "Partiu viagem","Vou viajar para São Paulo. Abraços!", maria);
		Post post2 = new Post(null,sdf.parse("23/03/2021") , "Bom dia","Acordei feliz hoje!", maria);
		
		//Abaixo salvamos os objetos (documents) no banco de dados
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		postRepository.saveAll(Arrays.asList(post1,post2));
	}

}
