package br.com.joaosbarbosa.backend.entities;

import br.com.joaosbarbosa.backend.utils.TablesName;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = TablesName.TABLE_PESSOA)
public class Person implements Serializable {

    @Id
    @Column(name = "id_pessoa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(name = "nome")
    private String firstName;

    @Column(name = "sobrenome")
    private String lastName;

    private String cpf;

    private String email;

    @Column(name = "senha")
    private String password;

    @Column(name = "endereco")
    private String address;

    @Column(name = "cep")
    private Integer zipCode;

    @Column(name = "data_criacao")
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private City city;

    public Person() {
    }

    public Person(
            Long personId,
            String cpf,
            String firstName,
            String lastName,
            String email,
            String password,
            String address,
            Integer zipCode,
            Date creationDate,
            City city) {
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
}
