package br.com.joaosbarbosa.backend.repositories;

import br.com.joaosbarbosa.backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = " SELECT * FROM tb_produtos WHERE pro_descricao_curta = :shortDescription AND pro_descricao_detalhada = :detailsDescription")
    Product getByShortAndDetailsDescription(@Param("shortDescription") String shortDescription, @Param("detailsDescription") String detailsDescription);
}
