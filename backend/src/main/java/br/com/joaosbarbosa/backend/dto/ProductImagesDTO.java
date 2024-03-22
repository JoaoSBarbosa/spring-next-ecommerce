package br.com.joaosbarbosa.backend.dto;

import java.io.Serializable;

import br.com.joaosbarbosa.backend.entities.Product;
import br.com.joaosbarbosa.backend.entities.ProductImages;
import org.springframework.web.multipart.MultipartFile;


public class ProductImagesDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idImage;
	private String name;
	private String uriImage;
	private Product product;
	private MultipartFile imageFile;
	
	public ProductImagesDTO() {}

	public ProductImagesDTO(Long idImage, String name, String uriImage, Product product, MultipartFile imageFile) {
		this.idImage = idImage;
		this.name = name;
		this.uriImage = uriImage;
		this.product = product;
		this.imageFile = imageFile;
	}

	public ProductImagesDTO(ProductImages entity) {

		idImage = entity.getIdImage();
		name = entity.getName();
		uriImage = entity.getUriImage();
//		product = entity.getProduct();
	}
	public Long getIdImage() {
		return idImage;
	}

	public void setIdImage(Long idImage) {
		this.idImage = idImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUriImage() {
		return uriImage;
	}

	public void setUriImage(String uriImage) {
		this.uriImage = uriImage;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
}
