package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.controllers.PersonClientController;
import br.com.joaosbarbosa.backend.dto.PersonClientRequestDTO;
import br.com.joaosbarbosa.backend.dto.PersonDTO;
import br.com.joaosbarbosa.backend.entities.Permission;
import br.com.joaosbarbosa.backend.entities.Person;
import br.com.joaosbarbosa.backend.repositories.PermissionRepository;
import br.com.joaosbarbosa.backend.repositories.PersonRepository;
import br.com.joaosbarbosa.backend.services.exceptions.ControllerNotFoundException;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PersonClientService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PermissionRepository permissionRepository;

    @Transactional
    public PersonDTO registrationClient(PersonClientRequestDTO personClientRequestDTO) {

        System.out.println(personClientRequestDTO);

        Set<Permission> permissionList = new HashSet<>();

        permissionList.add(permissionRepository.findById(4L).orElseThrow(() -> new ControllerNotFoundException("Não foi encontrado registros de permissões com o id informado: ")));
        Person person = new Person();


        BeanUtils.copyProperties(personClientRequestDTO, person);
        person.setPermissions(permissionList);
        person.setCreationDate(new Date());

        person = personRepository.save(person);
        System.out.println(person);
        return new PersonDTO(person, person.getPermissions());

    }

}
