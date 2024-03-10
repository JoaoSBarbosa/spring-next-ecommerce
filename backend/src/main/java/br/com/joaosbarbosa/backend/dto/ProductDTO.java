package br.com.joaosbarbosa.backend.dto;

import br.com.joaosbarbosa.backend.entities.Brand;
import br.com.joaosbarbosa.backend.entities.Category;
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
    private Category category;

    public ProductDTO() {
    }

    public ProductDTO(
            Long descriptionId,
            String shortDescription,
            String detailedDescription,
            Double saleValue,
            Double valueCost,
            Date creationDate,
            Date updateDate,
            Brand brand,
            Category category) {
        this.descriptionId = descriptionId;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.saleValue = saleValue;
        this.valueCost = valueCost;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.brand = brand;
        this.category = category;
    }

    public ProductDTO(Product product) {
        descriptionId = product.getProductId();
        shortDescription = product.getShortDescription();
        detailedDescription = product.getDetailedDescription();
        saleValue = product.getSaleValue();
        valueCost = product.getValueCost();
        creationDate = product.getCreationDate();
        updateDate = product.getUpdateDate();
        brand = product.getBrand();
        category = product.getCategory();
    }
}
