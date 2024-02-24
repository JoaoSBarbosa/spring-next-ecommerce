package br.com.joaosbarbosa.backend.dto;
import br.com.joaosbarbosa.backend.entities.State;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StateDTO implements Serializable {
    private Integer id;
    private String name;
    private String acronym;
    private Date creationDate;
    private Date updateDate;

    public StateDTO(){}
    public StateDTO(Integer id, String name, String acronym, Date creationDate, Date updateDate) {
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

}
