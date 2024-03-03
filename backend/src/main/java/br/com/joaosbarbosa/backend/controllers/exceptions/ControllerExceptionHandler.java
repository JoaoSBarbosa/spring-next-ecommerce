package br.com.joaosbarbosa.backend.controllers.exceptions;

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
    public ResponseEntity<StanderError> entityNotFound(ControllerNotFoundException exception,HttpServletRequest request){
        StanderError error = new StanderError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        error.setError("recurso não encontrado! 🤔🤷‍♂️");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
