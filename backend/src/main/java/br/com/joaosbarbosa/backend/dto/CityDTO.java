package br.com.joaosbarbosa.backend.dto;

import br.com.joaosbarbosa.backend.entities.City;
import br.com.joaosbarbosa.backend.entities.State;

import java.io.Serializable;
import java.util.Date;


public class CityDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String name;
    private Date createdDate;
    private Date updateDate;
    private State state;
    public CityDTO(){}

    public CityDTO(Long id, String name, Date createdDate, Date updateDate, State state) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.state = state;
    }

    public CityDTO(City entity) {
        id = entity.getCityId();
        name = entity.getName();
        createdDate = entity.getCreatedDate();
        updateDate = entity.getUpdateDate();
        state = entity.getState();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
    
    
}
