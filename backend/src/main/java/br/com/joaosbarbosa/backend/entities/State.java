package br.com.joaosbarbosa.backend.entities;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "tb_estado")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

//    @OneToMany(mappedBy = "state")
//    @JsonBackReference
//    private List<City> cities = new ArrayList<>();
    public State(){}

    public State(String name, String acronym, Date creationDate, Date updateDate) {
        this.name = name;
        this.acronym = acronym;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
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
