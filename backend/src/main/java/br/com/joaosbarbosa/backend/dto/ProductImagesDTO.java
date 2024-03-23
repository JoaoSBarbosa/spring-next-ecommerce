package br.com.joaosbarbosa.backend.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.joaosbarbosa.backend.entities.Product;
import br.com.joaosbarbosa.backend.entities.ProductImages;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ProductImagesDTO implements Serializable{

	
	private Long idImage;
	private String name;
	private String uriImage;
	private Product product;
	private MultipartFile imageFile;

	private Date creationDate;

	private Date updateDate;
	public ProductImagesDTO() {}

	public ProductImagesDTO(Long idImage, String name, String uriImage, Product product, MultipartFile imageFile, Date updateDate, Date creationDate) {
		this.idImage = idImage;
		this.name = name;
		this.uriImage = uriImage;
		this.product = product;
		this.imageFile = imageFile;
		this.updateDate = updateDate;
		this.creationDate = creationDate;
	}

	public ProductImagesDTO(ProductImages entity) {

		idImage = entity.getIdImage();
		name = entity.getName();
		uriImage = entity.getUriImage();
		product = entity.getProduct();
		updateDate = entity.getUpdateDate();
		creationDate = entity.getCreationDate();

	}

}
