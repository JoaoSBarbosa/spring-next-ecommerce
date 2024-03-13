package br.com.joaosbarbosa.backend.repositories;

import br.com.joaosbarbosa.backend.entities.Brand;
import br.com.joaosbarbosa.backend.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
