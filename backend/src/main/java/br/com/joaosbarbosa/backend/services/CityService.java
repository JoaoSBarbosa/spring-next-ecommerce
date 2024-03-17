package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.CityDTO;
import br.com.joaosbarbosa.backend.entities.City;
import br.com.joaosbarbosa.backend.entities.State;
import br.com.joaosbarbosa.backend.repositories.CityRepository;
import br.com.joaosbarbosa.backend.repositories.StateRepository;
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
public class CityService {

	@Autowired
	CityRepository cityRepository;
	@Autowired
	StateRepository stateRepository;

	@Transactional(readOnly = true)
	public CityDTO findById(Long id) {
		Optional<City> cityOptional = cityRepository.findById(id);

		City city = cityOptional.orElseThrow(()-> new ControllerNotFoundException("Nenhum registro foi encontrado com o ID informado (" + id + " 😣). Certifique-se de que o ID está correto e tente novamente. 🫡"));
		
		return new CityDTO(city);

	}

	@Transactional(readOnly = true)
	public Page<CityDTO> page(Pageable pageable) {
		Page<City> list = cityRepository.findAll(pageable);

		if (list.isEmpty()) {
			return null;

		}
		return list.map(CityDTO::new);
	}

	@Transactional
	public CityDTO insert(CityDTO dto) {

		City city = new City();
		copyDtoToEntity(dto, city);
		city = cityRepository.save(city);

		return new CityDTO(city);
	}

	@Transactional
	public CityDTO update(CityDTO dto, Long id) {

		try {

			City city = cityRepository.getReferenceById(id);

			city.setState(dto.getState());
			city.setName(dto.getName());
			city.setUpdateDate(new Date());

			city = cityRepository.save(city);

			return new CityDTO(city);

		} catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException(
					"Não existe registros de cidade cadstrado no sistema com o id informado: " + id);
		}
	}

	@Transactional
	public CityDTO delete(Long id) {
		Optional<City> cityOptional = cityRepository.findById(id);
		if (!cityOptional.isPresent()) {
			throw new ControllerNotFoundException(
					"Não existe registros de cidade cadstrado no sistema com o id informado: " + id);
		}

		cityRepository.deleteById(id);
		return null;

	}

	public void copyDtoToEntity(CityDTO source, City entity) {
		entity.setCityId(source.getId());
		entity.setName(source.getName());
		State state = stateRepository.findById(source.getState().getId()).orElseThrow(
				() -> new EntityNotFoundException("Estado não encontrado com o ID: " + source.getState().getId()));
		entity.setState(state);
		entity.setCreatedDate(new Date());
	}
}
