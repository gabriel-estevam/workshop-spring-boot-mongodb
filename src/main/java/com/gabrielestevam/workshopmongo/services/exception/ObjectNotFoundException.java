package com.gabrielestevam.workshopmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException {
//exceção personalizada para disparar quando objeto solicitado não foi encontrado
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
