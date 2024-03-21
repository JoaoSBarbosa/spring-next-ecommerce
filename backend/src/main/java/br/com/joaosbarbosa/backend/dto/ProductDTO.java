package br.com.joaosbarbosa.backend.dto;

import br.com.joaosbarbosa.backend.entities.Brand;
import br.com.joaosbarbosa.backend.entities.Category;
import br.com.joaosbarbosa.backend.entities.Product;
import br.com.joaosbarbosa.backend.entities.ProductImages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    
    private List<ProductImagesDTO> productImages = new ArrayList<>();
    
    public ProductDTO() {
    }



    public ProductDTO(Long descriptionId, String shortDescription, String detailedDescription, Double saleValue,
			Double valueCost, Date creationDate, Date updateDate, Brand brand, Category category) {
	
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

	public ProductDTO(Product product, List<ProductImages> images) {
		this(product);
		
		images.forEach(image -> this.productImages.add(new ProductImagesDTO(image)));
	}
	public Long getDescriptionId() {
		return descriptionId;
	}

	public void setDescriptionId(Long descriptionId) {
		this.descriptionId = descriptionId;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public Double getSaleValue() {
		return saleValue;
	}

	public void setSaleValue(Double saleValue) {
		this.saleValue = saleValue;
	}

	public Double getValueCost() {
		return valueCost;
	}

	public void setValueCost(Double valueCost) {
		this.valueCost = valueCost;
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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}



	public List<ProductImagesDTO> getProductImages() {
		return productImages;
	}




    
}
