package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.PersonDTO;
import br.com.joaosbarbosa.backend.entities.City;
import br.com.joaosbarbosa.backend.entities.Person;
import br.com.joaosbarbosa.backend.repositories.CityRepository;
import br.com.joaosbarbosa.backend.repositories.PersonRepository;
import br.com.joaosbarbosa.backend.services.exceptions.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static br.com.joaosbarbosa.backend.utils.Util.validateCPF;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Transactional(readOnly = true)
    public PersonDTO getById(Long personId) {
        Optional<Person> personOptional = personRepository.findById(personId);
        if (!personOptional.isPresent()) {
            return null;
        }
        String stringBuilder = personOptional.get().getFirstName() + " " + personOptional.get().getLastName();
        return new PersonDTO(personOptional.get());
    }

    @Transactional(readOnly = true)
    public Page<PersonDTO> page(Pageable pageable) {
        Page<Person> page = personRepository.findAll(pageable);

        if (page.isEmpty()) return null;

        return page.map(PersonDTO::new);

    }

    @Transactional
    public PersonDTO insert(PersonDTO source) {
        List<String> missingFields = findMissingRequiredFields(source);

        if (!missingFields.isEmpty()) {
            String message = "Os seguintes campos obrigatórios estão vazios: " + String.join(",", missingFields);
            return null;
        }
        if (!validateCPF(source.getCpf())) {
            return null;
        }
        Person person = new Person();
        copyDtoToEntity(source, person, false);
        person = personRepository.save(person);

        return new PersonDTO(person);

    }

    @Transactional
    public PersonDTO update(PersonDTO source, Long personId) {

        if (!validateCPF(source.getCpf())) {
            return null;
        }
        try {

            Person person = personRepository.getReferenceById(personId);
            copyDtoToEntity(source, person, true);
            person = personRepository.save(person);

            return new PersonDTO(person);
            
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Nenhuma cidade foi encontrada com o ID fornecido: " + source.getCity().getCityId());
        }

    }

    @Transactional
    public void delete(Long personId) {
        Optional<Person> personOptional = personRepository.findById(personId);
        if (personOptional.isPresent()) {
            personRepository.deleteById(personOptional.get().getPersonId());
          
        } else {
            throw new ControllerNotFoundException("Nenhuma cidade foi encontrada com o ID fornecido: " + personId);

        }
    }

    private List<String> findMissingRequiredFields(PersonDTO personDTO) {
        List<String> missingFields = new ArrayList<>();
        if (personDTO.getFirstName() == null || personDTO.getFirstName().isEmpty()) missingFields.add(" Nome ");
        if (personDTO.getLastName() == null || personDTO.getLastName().isEmpty()) missingFields.add(" Sobrenome ");
        if (personDTO.getCpf() == null || personDTO.getCpf().isEmpty()) missingFields.add(" CPF ");
        if (personDTO.getCity() == null) missingFields.add(" Cidade ");
        if (personDTO.getAddress() == null || personDTO.getAddress().isEmpty()) missingFields.add(" Endereço ");
        if (personDTO.getPassword() == null || personDTO.getPassword().isEmpty()) missingFields.add(" Senha ");
        if (personDTO.getEmail() == null || personDTO.getEmail().isEmpty()) missingFields.add(" E-mail ");
        if (personDTO.getZipCode() == null) missingFields.add(" CEP ");
        if (personDTO.getDistrict() == null || personDTO.getDistrict().isEmpty()) missingFields.add(" Bairro ");
        return missingFields;
    }

    @Autowired
    CityRepository cityRepository;

    private void copyDtoToEntity(PersonDTO source, Person entity, Boolean isUpdate) {
        entity.setAddress(source.getAddress());
        entity.setDistrict(source.getDistrict());
        entity.setCpf(source.getCpf());
        entity.setPassword(source.getPassword());
        entity.setEmail(source.getEmail());
        if (isUpdate) entity.setUpdateDate(new Date());
        if (!isUpdate) entity.setCreationDate(new Date());
        entity.setFirstName(source.getFirstName());
        entity.setLastName(source.getLastName());
        entity.setZipCode(source.getZipCode());

        City city = cityRepository.findById(source.getCity().getCityId()).orElseThrow(() -> new ControllerNotFoundException("Nenhuma cidade foi encontrada com o ID fornecido: " + source.getCity().getCityId()));
        entity.setCity(city);

    }

}
