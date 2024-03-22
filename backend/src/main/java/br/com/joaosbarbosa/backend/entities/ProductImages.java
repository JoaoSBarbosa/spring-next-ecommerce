package br.com.joaosbarbosa.backend.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_imagem_produto")
@JsonIgnoreProperties("product")
public class ProductImages implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_imagem")
	private Long idImage;
	
	@Column(name = "nome_imagem")
	private String name;
	
	@Column(name="url_imagem")
	private String uriImage;

	@ManyToOne
	@JoinColumn(name="id_produto")
	@JsonIgnore
	private Product product;
	
	public ProductImages() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdImage() {
		return idImage;
	}

	public void setIdImage(Long idImage) {
		this.idImage = idImage;
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
	
	
	

}
