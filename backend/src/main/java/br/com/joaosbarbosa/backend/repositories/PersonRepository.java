package br.com.joaosbarbosa.backend.repositories;

import br.com.joaosbarbosa.backend.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query( value = "SELECT p FROM Person p WHERE p.email = :email")
    Person findByEmail(@Param("email") String email);

    @Query(value = "SELECT p FROM Person p WHERE p.email = :email AND p.passwordRecoveryCode = :passwordRecoveryCode")
    Person findByEmailAndPasswordRecoveryCode(@Param("email") String email, @Param("passwordRecoveryCode") String passwordRecoveryCode);
}
