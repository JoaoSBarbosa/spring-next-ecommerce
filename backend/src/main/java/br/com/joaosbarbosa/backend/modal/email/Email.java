package br.com.joaosbarbosa.backend.modal.email;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Email {

    private String destination;
    private String title;
    private String message;
    private String recipientName;

    public Email(){}

    public Email(String destination, String title, String message, String recipientName) {
        this.destination = destination;
        this.title = title;
        this.message = message;
        this.recipientName = recipientName;
    }

}
