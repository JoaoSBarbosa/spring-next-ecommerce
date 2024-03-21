package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.BrandDTO;
import br.com.joaosbarbosa.backend.entities.Brand;
import br.com.joaosbarbosa.backend.repositories.BrandRepository;
import br.com.joaosbarbosa.backend.services.exceptions.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class BrandService {
	@Autowired
	BrandRepository brandRepository;

	@Transactional(readOnly = true)
	public BrandDTO getById(Long brand_id) {
		Optional<Brand> optionalBrand = brandRepository.findById(brand_id);

		if (!optionalBrand.isPresent()) {

			return null;
		}

		return new BrandDTO(optionalBrand.get());
	}

	@Transactional(readOnly = true)
	public Page<BrandDTO> page(Pageable pageable) {
		Page<Brand> page = brandRepository.findAll(pageable);

		if (page.isEmpty()) {
			return null;
		}
//        return page.map(brand -> new BrandDTO(brand));
		return page.map(BrandDTO::new);
	}

	@Transactional
	public BrandDTO insert(BrandDTO dto) {

		if (dto.getBrandName() == null) {
			return null;
		}

		Brand entity = new Brand();
		copyDtoToEntity(dto, entity, false);

		entity = brandRepository.save(entity);
		return new BrandDTO(entity);
	}

	@Transactional
	public BrandDTO update(BrandDTO dto, Long brandId) {
		System.out.println("CHEGOU O ID: " + brandId);
//        Optional<Brand> brandOptional = brandRepository.findById(brandId);
		try {

			Brand entity = brandRepository.getReferenceById(brandId);
			copyDtoToEntity(dto, entity, true);

			entity = brandRepository.save(entity);
			return new BrandDTO(entity);

		} catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException(
					"Não foi localizado registros de Marcas com o id informado: " + brandId);
		}
	}

	@Transactional
	public BrandDTO delete(Long brandId) {

		Optional<Brand> brand = brandRepository.findById(brandId);

		if (brand.isPresent()) {
			brandRepository.deleteById(brand.get().getBrandId());
			 return null;

		} else {
			throw new ControllerNotFoundException(
					"Não foi localizado registros de marcas com o id informado: " + brandId);
		}

	}

	private void copyDtoToEntity(BrandDTO source, Brand entity, Boolean isUpdate) {
		entity.setBrandId(source.getBrandId());
		entity.setBrandName(source.getBrandName());

		entity.setUpdateDate(isUpdate ? new Date() : entity.getUpdateDate());
		entity.setCreationdDate(!isUpdate ? new Date() : entity.getCreationdDate());

	}

}
