package br.com.joaosbarbosa.backend.controllers.exceptions;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class StanderError implements Serializable {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StanderError(){}

    public StanderError(Instant timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
