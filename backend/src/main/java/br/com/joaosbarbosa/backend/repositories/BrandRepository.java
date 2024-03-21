package br.com.joaosbarbosa.backend.repositories;

import br.com.joaosbarbosa.backend.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
