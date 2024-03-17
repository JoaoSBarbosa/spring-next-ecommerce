package br.com.joaosbarbosa.backend.dto;
import br.com.joaosbarbosa.backend.entities.State;

import java.io.Serializable;
import java.util.Date;

public class StateDTO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String name;
    private String acronym;
    private Date creationDate;
    private Date updateDate;

    public StateDTO(){}
    public StateDTO(Long id, String name, String acronym, Date creationDate, Date updateDate) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }
    public StateDTO(State state) {
        id = state.getId();
        name = state.getName();
        acronym = state.getAcronym();
        creationDate = state.getCreationDate();
        updateDate = state.getUpdateDate();
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
	public String getAcronym() {
		return acronym;
	}
	public void setAcronym(String acronym) {
		this.acronym = acronym;
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
    
    


}
