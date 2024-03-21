package br.com.joaosbarbosa.backend.entities;

import br.com.joaosbarbosa.backend.utils.TablesName;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = TablesName.TABLE_CATEGORIAS)
public class Category implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
