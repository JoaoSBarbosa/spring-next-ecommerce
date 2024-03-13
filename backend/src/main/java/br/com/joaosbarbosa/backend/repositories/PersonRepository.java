package br.com.joaosbarbosa.backend.repositories;

import br.com.joaosbarbosa.backend.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
