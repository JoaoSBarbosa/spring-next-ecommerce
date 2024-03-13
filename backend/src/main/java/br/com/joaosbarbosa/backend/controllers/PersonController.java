package br.com.joaosbarbosa.backend.controllers;

import br.com.joaosbarbosa.backend.dto.PersonDTO;
import br.com.joaosbarbosa.backend.services.PersonService;
import br.com.joaosbarbosa.backend.utils.api.ApiResponseHandler;
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
    public ResponseEntity<ApiResponseHandler> findById(@PathVariable Long personId) {
        ApiResponseHandler apiResponseHandler = personService.getById(personId);
        return new ResponseEntity<>(apiResponseHandler, apiResponseHandler.getStatus());
    }

    @GetMapping
    public ResponseEntity<Page<PersonDTO>> page(Pageable pageable) {
        Page<PersonDTO> page = personService.page(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping
    public ResponseEntity<ApiResponseHandler> insert(@RequestBody PersonDTO personDTO) {
        ApiResponseHandler apiResponseHandler = personService.insert(personDTO);
        return new ResponseEntity<>(apiResponseHandler, apiResponseHandler.getStatus());
    }

    @PutMapping("/{personId}")
    public ResponseEntity<ApiResponseHandler> update(@PathVariable Long personId, @RequestBody PersonDTO source) {
        ApiResponseHandler apiResponseHandler = personService.update(source, personId);
        return new ResponseEntity<>(apiResponseHandler, apiResponseHandler.getStatus());
    }
}
