package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.BrandDTO;
import br.com.joaosbarbosa.backend.entities.Brand;
import br.com.joaosbarbosa.backend.repositories.BrandRepository;
import br.com.joaosbarbosa.backend.services.exceptions.ControllerNotFoundException;
import br.com.joaosbarbosa.backend.utils.Util;
import br.com.joaosbarbosa.backend.utils.api.ApiResponseHandler;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Transactional(readOnly = true)
    public ApiResponseHandler getById(Long brand_id) {
        Optional<Brand> optionalBrand = brandRepository.findById(brand_id);

        if (!optionalBrand.isPresent()) {
            return ApiResponseHandler.builder()
                    .message("Não foi lacalizado registro de marcas com o id informado: " + brand_id)
                    .sendDateTime(Util.getDateTime())
                    .object(null)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return ApiResponseHandler.builder()
                .message("Marca '" + optionalBrand.get().getBrandName() + "' localidada!")
                .sendDateTime(Util.getDateTime())
                .object(optionalBrand.get())
                .status(HttpStatus.OK)
                .build();
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
    public ApiResponseHandler insert(BrandDTO dto) {

        if (dto.getBrandName() == null) {
            return ApiResponseHandler.builder()
                    .message("Por favor, insira o nome da marca")
                    .sendDateTime(Util.getDateTime())
                    .object(null)
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
        Brand entity = new Brand();
        copyDtoToEntity(dto, entity, false);

        entity = brandRepository.save(entity);
        return ApiResponseHandler.builder()
                .message("Marca '" + entity.getBrandName() + "' inserida com sucesso!")
                .sendDateTime(Util.getDateTime())
                .object(entity)
                .status(HttpStatus.OK)
                .build();
    }

    @Transactional
    public ApiResponseHandler update(BrandDTO dto, Long brandId) {
        System.out.println("CHEGOU O ID: " + brandId);
//        Optional<Brand> brandOptional = brandRepository.findById(brandId);
        try {

            Brand entity = brandRepository.getReferenceById(brandId);
            copyDtoToEntity(dto, entity, true);

            entity = brandRepository.save(entity);
            return ApiResponseHandler.builder()
                    .message("Marca '" + entity.getBrandName() + "' atualizada com sucesso!")
                    .sendDateTime(Util.getDateTime())
                    .object(entity)
                    .status(HttpStatus.OK)
                    .build();
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não foi localizado registros de Marcas com o id informado: " + brandId);
        }
    }
    
    @Transactional
    public ApiResponseHandler delete(Long brandId) {
        try {
            Optional<Brand> brand = brandRepository.findById(brandId);

            if (brand.isPresent()) {
                brandRepository.deleteById(brand.get().getBrandId());
                return ApiResponseHandler.builder()
                        .message("Marca '" + brand.get().getBrandName() + "' deletada com sucesso do sistema!")
                        .object(brand.get())
                        .sendDateTime(Util.getDateTime())
                        .status(HttpStatus.OK)
                        .build();
            } else {
                throw new ControllerNotFoundException("Não foi localizado registros de marcas com o id informado: " + brandId);
            }

        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Ocorreu um erro: " + e);
        }
    }

    private void copyDtoToEntity(BrandDTO source, Brand entity, Boolean isUpdate) {
        entity.setBrandId(source.getBrandId());
        entity.setBrandName(source.getBrandName());

        entity.setUpdateDate(isUpdate ? new Date() : entity.getUpdateDate());
        entity.setCreationdDate(!isUpdate ? new Date() : entity.getCreationdDate());

    }


}
