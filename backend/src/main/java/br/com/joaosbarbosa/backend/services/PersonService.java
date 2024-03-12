package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.PersonDTO;
import br.com.joaosbarbosa.backend.entities.Person;
import br.com.joaosbarbosa.backend.repositories.PersonRepository;
import br.com.joaosbarbosa.backend.utils.Util;
import br.com.joaosbarbosa.backend.utils.api.ApiResponseHandler;
import org.hibernate.type.descriptor.java.LongJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Transactional(readOnly = true)
    public ApiResponseHandler getById(Long personId) {
        Optional<Person> personOptional = personRepository.findById(personId);
        if (!personOptional.isPresent()) {
            return ApiResponseHandler.builder()
                    .object(null)
                    .status(HttpStatus.NOT_FOUND)
                    .sendDateTime(Util.getDateTime())
                    .message("Não foi localizado registros de pessoas com o id informado: " + personId)
                    .build();
        }
        String stringBuilder = personOptional.get().getFirstName() + " " + personOptional.get().getLastName();
        return ApiResponseHandler.builder()
                .object(personOptional.get())
                .status(HttpStatus.OK)
                .sendDateTime(Util.getDateTime())
                .message("Registro do '" + stringBuilder + "' localizado com sucesso!")
                .build();
    }

    @Transactional(readOnly = true)
    public Page<PersonDTO> page(Pageable pageable) {
        Page<Person> page = personRepository.findAll(pageable);

        if (page.isEmpty()) return null;

        return page.map(PersonDTO::new);

    }



}
