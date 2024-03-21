package br.com.joaosbarbosa.backend.dto;
import br.com.joaosbarbosa.backend.entities.Brand;
import java.io.Serializable;
import java.util.Date;


public class BrandDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long brandId;
    private String brandName;
    private Date creationdDate;
    private Date updateDate;
//    private List<ProductDTO> products = new ArrayList<>();
    public BrandDTO(Long brandId, String brandName, Date creationdDate, Date updateDate) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.creationdDate = creationdDate;
        this.updateDate = updateDate;
    }


    public BrandDTO(Brand entity) {
        brandId = entity.getBrandId();
        brandName = entity.getBrandName();
        creationdDate = entity.getCreationdDate();
        updateDate = entity.getUpdateDate();
    }


	public Long getBrandId() {
		return brandId;
	}


	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}


	public String getBrandName() {
		return brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}


	public Date getCreationdDate() {
		return creationdDate;
	}


	public void setCreationdDate(Date creationdDate) {
		this.creationdDate = creationdDate;
	}


	public Date getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

//    public BrandDTO(Brand entity, List<ProductDTO> products) {
//        this(entity);
//
//        products.forEach(productDTO -> this.products.add(new ProductDTO(productDTO)));
//
//    }
    
    
}
