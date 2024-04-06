package br.com.joaosbarbosa.backend.dto;

import br.com.joaosbarbosa.backend.entities.City;
import br.com.joaosbarbosa.backend.entities.Person;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class PersonClientRequestDTO {
    private Long personId;
    private String cpf;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String district;
    private String zipCode;
    private Date creationDate;
    private City city;



    public PersonClientRequestDTO(){}

    public PersonClientRequestDTO(Long personId, String cpf, String firstName, String lastName, String email, String address, String district, String zipCode, Date creationDate, City city) {
        this.personId = personId;
        this.cpf = cpf;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.district = district;
        this.zipCode = zipCode;
        this.creationDate = creationDate;
        this.city = city;
    }
}
