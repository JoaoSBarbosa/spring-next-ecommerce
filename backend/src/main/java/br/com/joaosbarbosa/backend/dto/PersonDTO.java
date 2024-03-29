package br.com.joaosbarbosa.backend.dto;
import br.com.joaosbarbosa.backend.entities.City;
import br.com.joaosbarbosa.backend.entities.Permission;
import br.com.joaosbarbosa.backend.entities.Person;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

@Data
public class PersonDTO implements Serializable {

	private Long personId;
    private String cpf;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String district;
    private String zipCode;
    private Date creationDate;
    private City city;
	List<PermissionDTO> permissions = new ArrayList<>();
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
            String district,
            String zipCode, Date creationDate, City city) {
        this.personId = personId;
        this.cpf = cpf;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.district = district;
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
        district = entity.getDistrict();
        zipCode = entity.getZipCode();
        creationDate = entity.getCreationDate();
        city = entity.getCity();
    }

	public PersonDTO(Person entity, Set<Permission> permissions) {
		this(entity);

		permissions.forEach(permission -> this.permissions.add(new PermissionDTO(permission)));

	}

    @Override
    public String toString() {
        return "PersonDTO{" +
                "personId=" + personId +
                ", cpf='" + cpf + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", creationDate=" + creationDate +
                ", city=" + city +
                ", permissions=" + permissions +
                '}';
    }
}
