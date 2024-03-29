package br.com.joaosbarbosa.backend.controllers.exceptions;

import br.com.joaosbarbosa.backend.services.exceptions.ControllerMissingRequiredFieldsException;
import br.com.joaosbarbosa.backend.services.exceptions.ControllerNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice // permite que a classe intercepte alguma exceção
public class ControllerExceptionHandler {


    @ExceptionHandler(ControllerNotFoundException.class)
    public ResponseEntity<StanderError> entityNotFound(ControllerNotFoundException exception, HttpServletRequest request) {
        StanderError error = new StanderError();

        error.setError("recurso não encontrado! 🤔🤷‍♂️");
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ControllerMissingRequiredFieldsException.class)
    public ResponseEntity<StanderError> missingFields(ControllerMissingRequiredFieldsException exception, HttpServletRequest request) {
        StanderError standerError = new StanderError();

        standerError.setTimestamp(Instant.now());
        standerError.setError("Campos obrigatórios estão ausentes 🛰️");
        standerError.setStatus(HttpStatus.BAD_REQUEST.value());
        standerError.setMessage(exception.getMessage());
        standerError.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standerError);
    }
}
