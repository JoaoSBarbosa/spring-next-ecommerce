package br.com.joaosbarbosa.backend.controllers;

import br.com.joaosbarbosa.backend.entities.Person;
import br.com.joaosbarbosa.backend.services.PersonManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person_manager")
public class personManagementController {

    @Autowired
    PersonManagementService personManagementService;

    @PostMapping("/password/request_code/")
    public ResponseEntity<String> getCode(@RequestBody Person person) {
        String message = personManagementService.getCode(person.getEmail());
        return ResponseEntity.ok().body(message);
    }

    @PostMapping("/password/change")
    public ResponseEntity<String> changePassword(@RequestBody Person person) {

        String message = personManagementService.changePassword(person);
        return ResponseEntity.ok().body(message);
    }

}
