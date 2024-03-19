package br.com.joaosbarbosa.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.joaosbarbosa.backend.entities.ProductImages;

@Repository
public interface ProductImagesRepository extends JpaRepository<ProductImages, Long>{

}
