package br.com.joaosbarbosa.backend.dto;

import br.com.joaosbarbosa.backend.entities.Brand;
import br.com.joaosbarbosa.backend.entities.Product;
import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {
    private Long descriptionId;
    private String shortDescription;
    private String detailedDescription;
    private Double saleValue;
    private Double valueCost;
    private Date creationDate;
    private Date updateDate;
    private Brand brand;

    public ProductDTO() {
    }

    public ProductDTO(Long descriptionId, String shortDescription, String detailedDescription, Double saleValue, Double valueCost, Date creationDate, Date updateDate, Brand brand) {
        this.descriptionId = descriptionId;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.saleValue = saleValue;
        this.valueCost = valueCost;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.brand = brand;
    }

    public ProductDTO(Product product) {
        this.descriptionId = product.getDescriptionId();
        this.shortDescription = product.getShortDescription();
        this.detailedDescription = product.getDetailedDescription();
        this.saleValue = product.getSaleValue();
        this.valueCost = product.getValueCost();
        this.creationDate = product.getCreationDate();
        this.updateDate = product.getUpdateDate();
        this.brand = product.getBrand();
    }
}
