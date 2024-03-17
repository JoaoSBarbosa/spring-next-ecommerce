package br.com.joaosbarbosa.backend.dto;
import br.com.joaosbarbosa.backend.entities.City;
import br.com.joaosbarbosa.backend.entities.Person;
import java.io.Serializable;
import java.util.Date;

public class PersonDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
    
    
}
