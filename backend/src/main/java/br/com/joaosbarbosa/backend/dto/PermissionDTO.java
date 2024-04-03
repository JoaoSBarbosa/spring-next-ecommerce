package br.com.joaosbarbosa.backend.dto;

import br.com.joaosbarbosa.backend.entities.Permission;
import br.com.joaosbarbosa.backend.entities.Person;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class PermissionDTO implements Serializable {

    private Long permissionId;
    private String name;

    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    public PermissionDTO(){}

    public PermissionDTO(Long permissionId, String name, LocalDateTime creationDate, LocalDateTime updateDate) {
        this.permissionId = permissionId;
        this.name = name;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }
    public PermissionDTO(Permission entity) {
        permissionId = entity.getPermissionId();
        name = entity.getName();
        creationDate = entity.getCreationDate();
        updateDate = entity.getUpdateDate();
    }



}
