package br.com.joaosbarbosa.backend.entities;

import br.com.joaosbarbosa.backend.utils.TablesName;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = TablesName.TABLE_CIDADES)
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cidade")
    private Long cityId;

    @Column(name = "nome")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_atualizacao")
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "estado")
    private State state;


    public City(){}

    public City(Long cityId, String name, Date createdDate, Date updateDate) {
        this.cityId = cityId;
        this.name = name;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long id) {
        this.cityId = id;
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
