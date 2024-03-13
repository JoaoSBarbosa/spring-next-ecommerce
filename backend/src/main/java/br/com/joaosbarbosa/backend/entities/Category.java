package br.com.joaosbarbosa.backend.entities;

import br.com.joaosbarbosa.backend.utils.TablesName;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data

@Entity
@Table(name = TablesName.TABLE_CATEGORIAS)
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long categoryId;
    @Column(name = "nome_categoria")
    private String name;
    @Column(name = "data_criacao")
    private Date creationDate;
    @Column(name = "data_atualizacao")
    private Date updateDate;

    public Category() {
    }

    public Category(Long categoryId, String name, Date creationDate, Date updateDate) {
        this.categoryId = categoryId;
        this.name = name;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public Category(String name, Date creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }
}
