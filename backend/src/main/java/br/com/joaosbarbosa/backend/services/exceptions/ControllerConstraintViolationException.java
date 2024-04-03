package br.com.joaosbarbosa.backend.services.exceptions;

public class ControllerConstraintViolationException extends RuntimeException{
    public ControllerConstraintViolationException(String message){
        super(message);
    }
}
