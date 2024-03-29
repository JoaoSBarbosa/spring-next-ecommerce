package br.com.joaosbarbosa.backend.controllers;

import br.com.joaosbarbosa.backend.dto.PersonClientRequestDTO;
import br.com.joaosbarbosa.backend.dto.PersonDTO;
import br.com.joaosbarbosa.backend.services.PersonClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client_person")
public class PersonClientController {

    @Autowired
    PersonClientService personClientService;

    @PostMapping
    public ResponseEntity<PersonDTO> insert(@RequestBody PersonClientRequestDTO personClientRequestDTO) {
        PersonDTO personDTO = personClientService.registrationClient(personClientRequestDTO);
        return ResponseEntity.ok().body(personDTO);
    }
}
