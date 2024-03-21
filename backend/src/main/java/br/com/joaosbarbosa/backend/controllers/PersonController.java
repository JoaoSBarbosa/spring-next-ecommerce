package br.com.joaosbarbosa.backend.controllers;

import br.com.joaosbarbosa.backend.dto.PersonDTO;
import br.com.joaosbarbosa.backend.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long personId) {
    	PersonDTO personDTO = personService.getById(personId);
        return  ResponseEntity.ok().body(personDTO);
    }

    @GetMapping
    public ResponseEntity<Page<PersonDTO>> page(Pageable pageable) {
        Page<PersonDTO> page = personService.page(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> insert(@RequestBody PersonDTO personDTO) {
    	personDTO = personService.insert(personDTO);
        return ResponseEntity.ok().body(personDTO);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<PersonDTO> update(@PathVariable Long personId, @RequestBody PersonDTO source) {
    	source = personService.update(source, personId);
        return  ResponseEntity.ok().body(source);
    }
}
