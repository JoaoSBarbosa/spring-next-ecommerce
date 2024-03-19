package br.com.joaosbarbosa.backend.entities;

import br.com.joaosbarbosa.backend.utils.TablesName;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = TablesName.TABLE_PRODUTOS)
public class Product implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id_produto")
    private Long productId;
    @Column(name = "pro_descricao_curta",unique = true)
    private String shortDescription;
    @Column(name = "pro_descricao_detalhada",unique = true)
    private String detailedDescription;
    @Column(name = "pro_valor_venda")
    private Double saleValue;
    @Column(name = "pro_valor_custo")
    private Double valueCost;
    @Column(name = "pro_data_criacao")
    private Date creationDate;
    @Column(name = "pro_data_atualizacao")
    private Date updateDate;

    // Muitos-para-um -> Muitos produtos associados a uma unica marca
    @ManyToOne
    @JoinColumn(name = "pro_id_marca")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "pro_id_categoria")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<ProductImages> productImages = new ArrayList<>();
    
    public Product(){}

    public Product(
            Long productId,
            String shortDescription,
            String detailedDescription,
            Double saleValue,
            Double valueCost,
            Date creationDate,
            Date updateDate,
            Brand brand,
            Category category)
    {
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public List<ProductImages> getProductImages() {
		return productImages;
	}


    
    
}

