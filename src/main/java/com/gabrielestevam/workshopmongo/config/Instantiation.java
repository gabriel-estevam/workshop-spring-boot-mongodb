package com.gabrielestevam.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielestevam.workshopmongo.domain.User;
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
	private UserRepository UserRepository;
	
	@Override
	public void run(String... args) throws Exception 
	{
	//implementação da interface CommandLineRunner (contrato).
	//Nesse metodo vamos instanciar os objetos e salvar no banco de dados
		UserRepository.deleteAll(); //aqui limpamos a coleção la no banco de dados
		
		//objetos a ser salvo no banco de dados ao iniciar a aplicação
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		UserRepository.saveAll(Arrays.asList(maria,alex,bob)); //salva os objetos no banco de dados
	}

}
