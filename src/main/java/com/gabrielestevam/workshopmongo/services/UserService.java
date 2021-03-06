package com.gabrielestevam.workshopmongo.services; //camada de serviço que conversa 
//com a camada repository e vai ser o serviço que trabalha com os usuários

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielestevam.workshopmongo.domain.User;
import com.gabrielestevam.workshopmongo.dto.UserDTO;
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
	
	public User insert(User obj) {
	//metodo par inserir um usuário (objeto)	
		return repo.insert(obj); //retorna um objeto ja inserido
	}
	
	public void delete(String id) {
	//metodo para deleta um usuário pelo seu id
		findById(id); //primerio ele busca pelo id, aproveitei o proprio metodo dessa classe
		//pois ele valida se o id é nulo ou não
		repo.deleteById(id); //deleta pelo id caso não seja nulo
	}
	
	public User update(User obj) {
	//metodo para atualizar os dados do usuário, tem como parametro um User obj
		
		User newObj = findById(obj.getId()); //instanciado um objeto que recebe um usuário do banco de dados, do obj passado como parametro
		//usado o findById da propria classe para validar se o objeto é nulo ou não, nesse caso ele valida o id que é tipo String
		updateData(newObj, obj); //metodo privado para atulizar os dados, ele copia os dados do obj que veio como parametro para o "newObj"
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		//metodo que copia os dados do obj e passa para o newobj
		newObj.setName(obj.getName()); //realiza a copia para o nome do usuário
		newObj.setEmail(obj.getEmail()); //realiza a copia para o email do usuário
	}

	public User fromDTO(UserDTO objDto) {
	/*Esse metodo vai pegar o DTO e instanciar um usuário, 
	 * inseri esse metodo na camada de serviço, pois essa o serviço
	 * tem dependencia a camada de acesso a dados, assim fica facil
	 * de dar manutenção, esse metodo poderia esta na classe UserDTO
	 */	
		return new User(objDto.getId(),objDto.getName(),objDto.getEmail()); //retorna um usuário convertendo o DTO para User
	}
}
