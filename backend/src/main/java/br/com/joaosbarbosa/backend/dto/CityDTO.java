package br.com.joaosbarbosa.backend.dto;

import br.com.joaosbarbosa.backend.entities.City;
import br.com.joaosbarbosa.backend.entities.State;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CityDTO implements Serializable {

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
}
