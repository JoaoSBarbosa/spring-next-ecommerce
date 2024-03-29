package br.com.joaosbarbosa.backend.services.exceptions;

public class ControllerMissingRequiredFieldsException extends RuntimeException {
    public ControllerMissingRequiredFieldsException(String message) {
        super(message);
    }
}
