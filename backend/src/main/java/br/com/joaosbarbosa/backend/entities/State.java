package br.com.joaosbarbosa.backend.entities;

import br.com.joaosbarbosa.backend.utils.TablesName;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Data
@Entity
@Table(name = "tb_estado")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    private String name;

    @Column(name = "sigla")
    private String acronym;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao")
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_atualizacao")
    private Date updateDate;

    public State(){}

    public State(String name, String acronym, Date creationDate, Date updateDate) {
        this.name = name;
        this.acronym = acronym;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }
}
