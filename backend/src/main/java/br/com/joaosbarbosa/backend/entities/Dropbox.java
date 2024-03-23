package br.com.joaosbarbosa.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "dropbox")
public class Dropbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientId;
    private String accessToken;
    private String refreshToken;
    private Date expirationDate;
    private Date tokenCreationDate;
    private Date tokenUpdateDate;

    public Dropbox(){}

    public Dropbox(Long id, String clientId, String accessToken, String refreshToken, Date expirationDate, Date tokenCreationDate, Date tokenUpdateDate) {
        this.id = id;
        this.clientId = clientId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expirationDate = expirationDate;
        this.tokenCreationDate = tokenCreationDate;
        this.tokenUpdateDate = tokenUpdateDate;
    }

}
