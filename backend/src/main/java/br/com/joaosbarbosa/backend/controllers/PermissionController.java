package br.com.joaosbarbosa.backend.controllers;

import br.com.joaosbarbosa.backend.dto.PermissionDTO;
import br.com.joaosbarbosa.backend.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @GetMapping("/{permissionId}")
    public ResponseEntity<PermissionDTO> findById(@PathVariable Long permissionId) {
        PermissionDTO permissionDTO = permissionService.findById(permissionId);
        return ResponseEntity.ok().body(permissionDTO);
    }

    @GetMapping
    public ResponseEntity<Page<PermissionDTO>> page(Pageable pageable) {
        Page<PermissionDTO> page = permissionService.page(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping
    public ResponseEntity<PermissionDTO> insert(@RequestBody PermissionDTO dto) {
        dto = permissionService.insert(dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{permissionId}")
    public ResponseEntity<PermissionDTO> update(@RequestBody PermissionDTO source, @PathVariable Long permissionId) {
        source = permissionService.update(source, permissionId);
        return ResponseEntity.ok().body(source);
    }

    @DeleteMapping("/{permissionId}")
    public void delete(@PathVariable Long permissionId) {
        permissionService.delete(permissionId);
    }
}
