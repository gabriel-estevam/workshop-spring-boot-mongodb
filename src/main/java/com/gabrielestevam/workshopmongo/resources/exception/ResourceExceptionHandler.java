package com.gabrielestevam.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gabrielestevam.workshopmongo.services.exception.ObjectNotFoundException;
//Essa classe vai ser o manipulador de exceções da camada de resource, isto é ela vai distribuir o erro de acordo com que 
//foi gerado, para assim não ficar gerando o erro 500, por exemplo se acontecer um erro de objeto não encotrado, trataremos
//para o retorno do tipo "404 not found"

//essa anotação indica que a classe é um controlador de REST
@RestControllerAdvice
public class ResourceExceptionHandler 
{
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) 
	{
		//metodo para tratar o erro de objeto não encontrado "404 not found"
		/*Esse metodo é do tipo StandardError, que é aquela estrutura padrão do API REST;
		 * temo como parametro:
		 * ObjectNotFoundException - que é a classe personalizada;
		 * HttpServerLetRequest - que é uma exigencia do freamwork (captura a requisição)*/
		
		HttpStatus status = HttpStatus.NOT_FOUND; //Objeto que vai retornar o status de um objeto não encontrado 
		/*Quando acontecer um erro no serviço, vamos instanciar a classe StandardError passando como parametro,
		 * o instante atual do sistema - usado o System.CurrentTimeMillis();
		 * o status, que o objeto instanciado acima
		 * erro - "não encontrado"
		 * a mensagem da exceção
		 * e o caminho que pagamos do parametro do Metodo (getRequestURI retorna par tipo String)*/
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err); //ao final retorna o status e no corpo da requisição o argumento que é
		//o objeto StanderError
	}
}
