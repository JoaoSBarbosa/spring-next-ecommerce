package br.com.joaosbarbosa.backend.utils.api;

import lombok.Builder;
import org.springframework.http.HttpStatus;


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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getSendDateTime() {
		return sendDateTime;
	}

	public void setSendDateTime(String sendDateTime) {
		this.sendDateTime = sendDateTime;
	}
    
    


}



