package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.PermissionDTO;
import br.com.joaosbarbosa.backend.dto.PersonDTO;
import br.com.joaosbarbosa.backend.entities.City;
import br.com.joaosbarbosa.backend.entities.Permission;
import br.com.joaosbarbosa.backend.entities.Person;
import br.com.joaosbarbosa.backend.repositories.CityRepository;
import br.com.joaosbarbosa.backend.repositories.PermissionRepository;
import br.com.joaosbarbosa.backend.repositories.PersonRepository;
import br.com.joaosbarbosa.backend.services.exceptions.ControllerMissingRequiredFieldsException;
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
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    CityRepository cityRepository;


    @Transactional(readOnly = true)
    public PersonDTO getById(Long personId) {
        Optional<Person> personOptional = personRepository.findById(personId);
        return personOptional.map(person -> new PersonDTO(person, person.getPermissions())).orElse(null);
    }

    @Transactional(readOnly = true)
    public Page<PersonDTO> page(Pageable pageable) {
        Page<Person> page = personRepository.findAll(pageable);

        if (page.isEmpty()) return null;

        return page.map(person -> new PersonDTO(person, person.getPermissions()));

    }


    @Transactional
    public PersonDTO insert(PersonDTO source) {
        List<String> missingFields = findMissingRequiredFields(source);

        if (!missingFields.isEmpty()) {
            throw new ControllerMissingRequiredFieldsException("Os seguintes campos obrigatórios estão vazios: "+ String.join(",", missingFields));
        }
        if (!validateCPF(source.getCpf())) {
            throw new ControllerNotFoundException("cpf inválido: " + source.getCpf());
        }

        Person person = new Person();
        copyDtoToEntity(source, person, false);
        person = personRepository.save(person);

        return new PersonDTO(person, person.getPermissions());

    }

    @Transactional
    public PersonDTO update(PersonDTO source, Long personId) {


        if (source.getCpf() != null && !validateCPF(source.getCpf())) {
            return null;
        }
        try {

            Person person = personRepository.getReferenceById(personId);
            copyDtoToEntity(source, person, true);
            person = personRepository.save(person);

            return new PersonDTO(person, person.getPermissions());

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

    private void copyDtoToEntity(PersonDTO source, Person entity, Boolean isUpdate) {
        if (source.getAddress() != null) entity.setAddress(source.getAddress());
        if (source.getDistrict() != null) entity.setDistrict(source.getDistrict());
        if (source.getCpf() != null) entity.setCpf(source.getCpf());
        if (source.getPassword() != null) entity.setPassword(source.getPassword());
        if (source.getEmail() != null) entity.setEmail(source.getEmail());
        if (isUpdate) entity.setUpdateDate(new Date());
        if (!isUpdate) entity.setCreationDate(new Date());
        if (source.getFirstName() != null) entity.setFirstName(source.getFirstName());
        if (source.getLastName() != null) entity.setLastName(source.getLastName());
        if (source.getZipCode() != null) entity.setZipCode(source.getZipCode());
        if (source.getCity() != null) {
            City city = cityRepository.findById(source.getCity().getCityId()).orElseThrow(() -> new ControllerNotFoundException("Nenhuma cidade foi encontrada com o ID fornecido: " + source.getCity().getCityId()));
            entity.setCity(city);
        }

        entity.getPermissions().clear();

        for (PermissionDTO dto : source.getPermissions()) {

            Permission permission = permissionRepository.getReferenceById(dto.getPermissionId());

            entity.getPermissions().add(permission);
        }
    }

}
