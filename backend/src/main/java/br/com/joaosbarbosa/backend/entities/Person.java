package br.com.joaosbarbosa.backend.entities;

import br.com.joaosbarbosa.backend.utils.TablesName;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Data
@Table(name = TablesName.TABLE_PESSOA)
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id_pessoa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(name = "nome")
    private String firstName;

    @Column(name = "sobrenome")
    private String lastName;

    @CPF
    private String cpf;

    @Email
    private String email;

    @Column(name = "senha")
    private String password;

    @Column(name = "codigo_recuperacaoo_senha")
    private String passwordRecoveryCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_envio_codigo")
    private Date codeSendDate;

    @Column(name = "endereco")
    private String address;

    @Column(name = "bairro")
    private String district;

    @Column(name = "cep")
    private String zipCode;

    @Column(name = "data_criacao")
	@Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "data_atualizacao")
	@Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private City city;

	@ManyToMany
	@JoinTable(
			name = "tb_permissao_pessoa",
			joinColumns = @JoinColumn(name ="pep_id_pessoa"),
			inverseJoinColumns = @JoinColumn(name = "pep_id_permissao"))
	Set<Permission> permissions = new HashSet<>();



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
            String district,
            String zipCode,
            Date creationDate,
            City city) {
        this.personId = personId;
        this.cpf = cpf;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.district = district;
        this.zipCode = zipCode;
        this.creationDate = creationDate;
        this.city = city;
    }

    
}
