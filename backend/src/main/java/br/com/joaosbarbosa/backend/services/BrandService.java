package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.BrandDTO;
import br.com.joaosbarbosa.backend.entities.Brand;
import br.com.joaosbarbosa.backend.repositories.BrandRepository;
import br.com.joaosbarbosa.backend.utils.Util;
import br.com.joaosbarbosa.backend.utils.api.ApiResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

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
        copyDtoToEntity(dto, entity);

        entity = brandRepository.save(entity);
        return ApiResponseHandler.builder()
                .message("Marca '" + entity.getBrandName() + "' inserida com sucesso!")
                .sendDateTime(Util.getDateTime())
                .object(entity)
                .status(HttpStatus.OK)
                .build();
    }

    private void copyDtoToEntity(BrandDTO source, Brand entity) {
        entity.setBrandId(source.getBrandId());
        entity.setBrandName(source.getBrandName());
        entity.setCreationdDate(new Date());
    }
}
