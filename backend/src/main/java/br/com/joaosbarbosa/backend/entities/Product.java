package br.com.joaosbarbosa.backend.entities;

import br.com.joaosbarbosa.backend.utils.TablesName;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = TablesName.TABLE_PRODUTOS)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id_descricao")
    private Long descriptionId;
    @Column(name = "pro_descricao_curta")
    private String shortDescription;
    @Column(name = "pro_descricao_detalhada")
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

    public Product(){}

    public Product(Long descriptionId, String shortDescription, String detailedDescription, Double saleValue, Double valueCost, Date creationDate, Date updateDate, Brand brand) {
        this.descriptionId = descriptionId;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.saleValue = saleValue;
        this.valueCost = valueCost;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.brand = brand;
    }
}
