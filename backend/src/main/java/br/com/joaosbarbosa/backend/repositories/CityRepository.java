package br.com.joaosbarbosa.backend.repositories;

import br.com.joaosbarbosa.backend.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
