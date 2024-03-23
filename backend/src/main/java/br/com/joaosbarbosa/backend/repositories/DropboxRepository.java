package br.com.joaosbarbosa.backend.repositories;

import br.com.joaosbarbosa.backend.entities.Dropbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DropboxRepository extends JpaRepository<Dropbox, Long> {
}
