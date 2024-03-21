package br.com.joaosbarbosa.backend.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_permissao")
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permissao")
    private Long permissionId;

    @Column(name = "nome_permissao")
    private String name;

    @Column(name = "data_criacao")
    private Date creationDate;

    @Column(name = "data_atualizacao")
    private Date updateDate;

    @ManyToMany(mappedBy = "personPermission")
    private Set<Person> persons = new HashSet<>();
}
