package br.com.joaosbarbosa.backend.entities;

import br.com.joaosbarbosa.backend.utils.TablesName;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = TablesName.TABLE_MARCAS)
public class Brand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Long brandId;

    @Column(name = "nome_marca")
    private String brandName;

    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdDate;

    @Column(name = "data_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    // um-para-muitos -> Uma marca associada para varios produtos
//    @OneToMany(mappedBy = "brand")
//    private List<Product> products = new ArrayList<>();

    public Brand(){}
    public Brand(Long brandId, String brandName, Date creationdDate, Date updateDate) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.creationdDate = creationdDate;
        this.updateDate = updateDate;
    }
}
