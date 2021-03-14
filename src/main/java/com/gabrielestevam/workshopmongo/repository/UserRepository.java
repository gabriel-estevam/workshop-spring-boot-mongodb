package com.gabrielestevam.workshopmongo.repository; //camada de repositorio, essa camada "conversa" com a camada de serviço

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gabrielestevam.workshopmongo.domain.User;

//anotação dizendo que essa interface é um repositório
@Repository
public interface UserRepository extends MongoRepository<User, String> {
/*essa interface acessa a base de dados do MongoDb, para isso ela herda (extends
MongoRepository - passamos como parametro a entidade do dominio User, e o tipo de dado
do id (atributo) da classe que nesse caso é String. Com essa interface criada sera 
possivel fazer varias operações basicas com os usuários (salvar, atualizar, deletar, inserir)
*/
}
