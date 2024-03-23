package br.com.joaosbarbosa.backend.controllers;

import br.com.joaosbarbosa.backend.entities.Dropbox;
import br.com.joaosbarbosa.backend.services.DropboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dropbox")
public class DropboxController {
    @Autowired
    DropboxService dropboxService;

    @PostMapping
    public ResponseEntity<Dropbox> post(@RequestBody Dropbox dropbox) {
        dropbox = dropboxService.insert(dropbox);
        return ResponseEntity.ok().body(dropbox);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dropbox> getById(@PathVariable Long id) {
        Dropbox dropbox = dropboxService.getById(id);
        return ResponseEntity.ok().body(dropbox);
    }

    @GetMapping
    public ResponseEntity<Page<Dropbox>> page(Pageable pageable) {
        Page<Dropbox> dropbox = dropboxService.page(pageable);
        return ResponseEntity.ok().body(dropbox);
    }
}
