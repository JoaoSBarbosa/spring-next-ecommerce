package br.com.joaosbarbosa.backend.entities;

import br.com.joaosbarbosa.backend.utils.TablesName;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	Set<Permission> personPermission = new HashSet<>();

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

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
    
    
}
