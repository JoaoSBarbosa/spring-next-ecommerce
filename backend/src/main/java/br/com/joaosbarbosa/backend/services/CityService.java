package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.CityDTO;
import br.com.joaosbarbosa.backend.entities.City;
import br.com.joaosbarbosa.backend.entities.State;
import br.com.joaosbarbosa.backend.repositories.CityRepository;
import br.com.joaosbarbosa.backend.repositories.StateRepository;
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
public class CityService {

    @Autowired
    CityRepository cityRepository;
    @Autowired
    StateRepository stateRepository;

    @Transactional(readOnly = true)
    public ApiResponseHandler findById(Long id) {
        Optional<City> cityOptional = cityRepository.findById(id);

        if (!cityOptional.isPresent()) {
            return ApiResponseHandler.builder()
                    .message("Cidade não encontrado através do código informado: " + id)
                    .status(HttpStatus.NOT_FOUND)
                    .sendDateTime(Util.getDateTime())
                    .object(null)
                    .build();
        }

        return ApiResponseHandler.builder()
                .message("Registro de cidade localizado")
                .object(cityOptional.get())
                .sendDateTime(Util.getDateTime())
                .status(HttpStatus.OK)
                .build();
    }


    @Transactional(readOnly = true)
    public Page<CityDTO> page(Pageable pageable) {
        Page<City> list = cityRepository.findAll(pageable);

        if (list.isEmpty()) {
            System.out.println("NÃO TEM NADA");
            return null;

        }
        System.out.println("TEM ITEM");
        return list.map(CityDTO::new);
    }

    @Transactional
    public ApiResponseHandler insert(CityDTO dto) {

        System.out.println("TESTE" + dto.getState());
        City city = new City();
        city.setCityId(dto.getId());
        city.setName(dto.getName());
        State state = stateRepository.findById(dto.getState().getId())
                .orElseThrow(() -> new EntityNotFoundException("Estado não encontrado com o ID: " + dto.getState().getId()));


        city.setState(state);
        city.setCreatedDate(new Date());
        city = cityRepository.save(city);

        return ApiResponseHandler.builder()
                .message("Cidade salva com sucesso!")
                .status(HttpStatus.OK)
                .sendDateTime(Util.getDateTime())
                .object(city)
                .build();
    }

    @Transactional
    public ApiResponseHandler update(CityDTO dto, Long id) {
        Optional<City> cityOptional = cityRepository.findById(id);

        if (!cityOptional.isPresent()) {
            return ApiResponseHandler.builder()
                    .message("Não existe registros de cidade cadstrado no sistema com o id informado: " + id)
                    .status(HttpStatus.NOT_FOUND)
                    .sendDateTime(Util.getDateTime())
                    .object(null)
                    .build();
        }

        City city = cityOptional.get();

        city.setState(dto.getState());
        city.setName(dto.getName());
        city.setUpdateDate(new Date());

        city = cityRepository.save(city);

        return ApiResponseHandler.builder()
                .message("Cidade atualizada com sucesso!")
                .status(HttpStatus.OK)
                .sendDateTime(Util.getDateTime())
                .object(city)
                .build();
    }

    @Transactional
    public ApiResponseHandler delete(Long id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        if (!cityOptional.isPresent()) {
            return ApiResponseHandler.builder()
                    .message("Não existe registros de cidade cadstrado no sistema com o id informado: " + id)
                    .status(HttpStatus.NOT_FOUND)
                    .sendDateTime(Util.getDateTime())
                    .object(null)
                    .build();
        }

        cityRepository.deleteById(id);
        return ApiResponseHandler.builder()
                .message("Cidade '" + cityOptional.get().getName() + "' deletada com sucesso!")
                .status(HttpStatus.OK)
                .sendDateTime(Util.getDateTime())
                .object(null)
                .build();

    }
}
