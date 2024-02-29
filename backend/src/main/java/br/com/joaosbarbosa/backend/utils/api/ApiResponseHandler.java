package br.com.joaosbarbosa.backend.utils.api;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponseHandler {
    private String message;
    private HttpStatus status;
    private Object object;
    private String sendDateTime;

    public ApiResponseHandler() {
    }

    public ApiResponseHandler(String message, HttpStatus status, Object object, String sendDateTime) {
        this.message = message;
        this.status = status;
        this.object = object;
        this.sendDateTime = sendDateTime;
    }
}



