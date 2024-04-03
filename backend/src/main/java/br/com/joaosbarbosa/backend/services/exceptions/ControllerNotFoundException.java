package br.com.joaosbarbosa.backend.services.exceptions;

public class ControllerNotFoundException extends RuntimeException{
	public ControllerNotFoundException(String message){
        super(message);
    }
}
