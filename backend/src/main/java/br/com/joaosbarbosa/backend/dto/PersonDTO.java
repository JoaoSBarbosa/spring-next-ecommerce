package br.com.joaosbarbosa.backend.dto;

import br.com.joaosbarbosa.backend.entities.City;
import br.com.joaosbarbosa.backend.entities.Person;
import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

@Data
public class PersonDTO implements Serializable {

    private Long personId;
    private String cpf;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private Integer zipCode;
    private Date creationDate;
    private City city;

    public PersonDTO() {
    }

    public PersonDTO(
            Long personId,
            String cpf,
            String firstName,
            String lastName,
            String email,
            String password,
            String address,
            Integer zipCode, Date creationDate, City city) {
        this.personId = personId;
        this.cpf = cpf;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.zipCode = zipCode;
        this.creationDate = creationDate;
        this.city = city;
    }

    public PersonDTO(Person entity) {
        personId = entity.getPersonId();
        cpf = entity.getCpf();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        email = entity.getEmail();
        password = entity.getPassword();
        address = entity.getAddress();
        zipCode = entity.getZipCode();
        creationDate = entity.getCreationDate();
        city = entity.getCity();
    }
}
