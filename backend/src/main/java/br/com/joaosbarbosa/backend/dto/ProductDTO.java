package br.com.joaosbarbosa.backend.dto;

import br.com.joaosbarbosa.backend.entities.Brand;
import br.com.joaosbarbosa.backend.entities.Category;
import br.com.joaosbarbosa.backend.entities.Product;
import br.com.joaosbarbosa.backend.entities.ProductImages;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ProductDTO {
	//	public ProductDTO(Product product, List<ProductImages> images) {
	//		this(product);
	//
	//		images.forEach(image -> this.productImages.add(new ProductImagesDTO(image)));
	//	}
	private Long productId;
    private String shortDescription;
    private String detailedDescription;
    private Double saleValue;
    private Double valueCost;
    private Date creationDate;
    private Date updateDate;
    private Brand brand;
    private Category category;
    
//    private List<ProductImagesDTO> productImages = new ArrayList<>();

//	private List<ProductImages> images = new ArrayList<>();


	public ProductDTO() {
    }



    public ProductDTO(Long productId, String shortDescription, String detailedDescription, Double saleValue,
					  Double valueCost, Date creationDate, Date updateDate, Brand brand, Category category) {
	
		this.productId = productId;
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
        productId = product.getProductId();
        shortDescription = product.getShortDescription();
        detailedDescription = product.getDetailedDescription();
        saleValue = product.getSaleValue();
        valueCost = product.getValueCost();
        creationDate = product.getCreationDate();
        updateDate = product.getUpdateDate();
        brand = product.getBrand();
        category = product.getCategory();
    }


//	public List<ProductImagesDTO> getProductImages() {
//		return productImages;
//	}




    
}
