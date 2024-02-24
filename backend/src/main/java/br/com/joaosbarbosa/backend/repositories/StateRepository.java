package br.com.joaosbarbosa.backend.repositories;

import br.com.joaosbarbosa.backend.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM tb_estado WHERE nome = :name")
    Optional<State> findByName(@Param("name") String name);
}
