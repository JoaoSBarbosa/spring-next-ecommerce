package br.com.joaosbarbosa.backend.dto;

import br.com.joaosbarbosa.backend.entities.Category;

import java.io.Serializable;
import java.util.Date;


public class CategoryDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long categoryId;
    private String name;
    private Date creationDate;
    private Date updateDate;

    public CategoryDTO() {}

    public CategoryDTO(Long categoryId, String name, Date creationDate, Date updateDate) {
        this.categoryId = categoryId;
        this.name = name;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public CategoryDTO(Category entity) {
        categoryId = entity.getCategoryId();
        name = entity.getName();
        creationDate = entity.getCreationDate();
        updateDate = entity.getUpdateDate();
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
