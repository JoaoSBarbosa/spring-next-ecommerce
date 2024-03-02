package br.com.joaosbarbosa.backend.dto;

import br.com.joaosbarbosa.backend.entities.Brand;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BrandDTO implements Serializable {

    private Long brandId;
    private String brandName;
    private Date creationdDate;
    private Date updateDate;

    public BrandDTO(Long brandId, String brandName, Date creationdDate, Date updateDate) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.creationdDate = creationdDate;
        this.updateDate = updateDate;
    }

    public BrandDTO(Brand entity) {
        brandId = entity.getBrandId();
        brandName = entity.getBrandName();
        creationdDate = entity.getCreationdDate();
        updateDate = entity.getUpdateDate();
    }
}
