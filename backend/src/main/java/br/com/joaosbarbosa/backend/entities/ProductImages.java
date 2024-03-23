package br.com.joaosbarbosa.backend.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "tb_imagem_produto")
public class ProductImages implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_imagem")
	private Long idImage;
	
	@Column(name = "nome_imagem")
	private String name;
	
	@Column(name="url_imagem")
	private String uriImage;

	@Column(name = "data_criacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column(name = "data_atualizacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@JsonIgnore // Ignora a serialização desta propriedade
	@ManyToOne
	@JoinColumn(name="id_produto")
	private Product product;
	
	public ProductImages() {
	}

	public ProductImages(Long idImage, String name, String uriImage, Date creationDate, Date updateDate, Product product) {
		this.idImage = idImage;
		this.name = name;
		this.uriImage = uriImage;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.product = product;
	}


}
