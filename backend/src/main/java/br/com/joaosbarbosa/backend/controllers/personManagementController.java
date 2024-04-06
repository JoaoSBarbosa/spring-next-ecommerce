package br.com.joaosbarbosa.backend.controllers;
import br.com.joaosbarbosa.backend.services.PersonManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person_manager")
public class personManagementController {

    @Autowired
    PersonManagementService personManagementService;

    @PostMapping("/request/code")
    public ResponseEntity<String> getCode(@RequestParam("email") String email) {
        String message = personManagementService.getCode(email);
        return ResponseEntity.ok().body(message);
    }

}
