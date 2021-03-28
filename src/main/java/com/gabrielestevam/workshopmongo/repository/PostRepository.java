package com.gabrielestevam.workshopmongo.repository; //camada de repositorio, essa camada "conversa" com a camada de serviço

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabrielestevam.workshopmongo.domain.Post;

//anotação dizendo que essa interface é um repositório
@Repository
public interface PostRepository extends MongoRepository<Post, String> {
/*essa interface acessa a base de dados do MongoDb, para isso ela herda (extends
MongoRepository - passamos como parametro a entidade do dominio Post, e o tipo de dado
do id (atributo) da classe que nesse caso é String. Com essa interface criada sera 
possivel fazer varias operações basicas com os usuários (salvar, atualizar, deletar, inserir)
*/
	
	/*anotação permite que possamos entrar com uma consulta no MongoDB no jeito JSON, dessa forma deixamos personalizada
	 *isto é, do nosso jeito, com isso podemos dar o nome que quisermos ao metodo
	 *
	 * As sintaxes das consultas é encontrada na documentação do MongoDB
	 * { <field>: { $regex: /pattern/, $options: '<options>' } }
	 * field - é o campo onde vamos usar na consulta
	 * $regex: Expressão regular, essa expressão pegamos do parametro do metodo - text;
	 * 			 ?0 - indica que queremos o primeiro parametro, se tivesse mais de 1, colocaria ?1
	 * $options: 'i' - letra 'i' indica que não sera Case insensitivity - considera upper e lower cases
	 */
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	/*Query method - metodo para buscar informações no MongoDB,
	 * regra de sintaxe: findByNOME_DO_CAMPOCONDICAO,
	 * o Spring boot consegue "Montar" um metodo para buscas automaticamente, basta seguir
	 * a regra de sintaxe, onde apos o "findBy" temos que colocar o nome da coluna a ser buscada.
	 * Na linha abaixo vamos retorna uma lista de Post  cujo os Titulos contantendo (containing) um frase.
	 * O IgnoreCase indica ao Spring para ignorar maisculas e minusculas*/
	List<Post> findByTitleContainingIgnoreCase(String text); //metodo de busca
}
