package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.PermissionDTO;
import br.com.joaosbarbosa.backend.entities.Permission;
import br.com.joaosbarbosa.backend.repositories.PermissionRepository;
import br.com.joaosbarbosa.backend.repositories.PersonRepository;
import br.com.joaosbarbosa.backend.services.exceptions.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PermissionService {
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    PersonRepository personRepository;

    @Transactional
    public PermissionDTO insert(PermissionDTO dto) {
        Permission permission = new Permission();

        permission.setName(dto.getName());
        permission.setCreationDate(LocalDateTime.now());
        permission = permissionRepository.save(permission);
        return new PermissionDTO(permission);
    }

    @Transactional(readOnly = true)
    public Page<PermissionDTO> page(Pageable pageable) {
        Page<Permission> permissions = permissionRepository.findAll(pageable);
        if (permissions.isEmpty()) {
            System.out.println("Tabela vazia");
            return null;
        }
        return permissions.map(PermissionDTO::new);
    }

    @Transactional(readOnly = true)
    public PermissionDTO findById(Long permissionId) {
        try {
            Permission permission = permissionRepository.getReferenceById(permissionId);
            return new PermissionDTO(permission);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não foi possivel localizar registros de pessmisões com o id informado: " + permissionId);
        }
    }

    @Transactional
    public PermissionDTO update(PermissionDTO source, Long permissionId) {
        try {
            Permission permission = permissionRepository.getReferenceById(permissionId);

            permission.setUpdateDate(LocalDateTime.now());
            if (source.getName() != null) permission.setName(source.getName());
            permission = permissionRepository.save(permission);
            return new PermissionDTO(permission);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não foi possivel localizar registros de pessmisões com o id informado: " + permissionId);
        }
    }

    @Transactional
    public void delete(Long permissionId) {
        try {
            Permission permission = permissionRepository.getReferenceById(permissionId);

            permissionRepository.deleteById(permission.getPermissionId());
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não foi possivel localizar registros de pessmisões com o id informado: " + permissionId);
        }
    }
}