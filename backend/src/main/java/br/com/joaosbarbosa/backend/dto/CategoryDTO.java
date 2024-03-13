package br.com.joaosbarbosa.backend.dto;

import br.com.joaosbarbosa.backend.entities.Category;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CategoryDTO implements Serializable {
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
}
