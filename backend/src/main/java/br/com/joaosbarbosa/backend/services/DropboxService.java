package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.entities.Dropbox;
import br.com.joaosbarbosa.backend.repositories.DropboxRepository;
import br.com.joaosbarbosa.backend.services.exceptions.ControllerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DropboxService {
    @Autowired
    DropboxRepository repository;

    @Transactional
    public Dropbox insert(Dropbox dropbox) {
        Dropbox entity = new Dropbox();
        System.out.println(dropbox.getAccessToken());
        entity.setAccessToken(dropbox.getAccessToken());
//        entity.setExpirationDate(dropbox.getExpirationDate());
//        entity.setRefreshToken(dropbox.getRefreshToken());
//        entity.setTokenCreationDate(dropbox.getTokenCreationDate());
//        entity.setTokenUpdateDate(dropbox.getTokenUpdateDate());
        entity = repository.save(dropbox);

        return entity;
    }

    @Transactional(readOnly = true)
    public Dropbox getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Não localizado com id informando: " + id));
    }

    @Transactional(readOnly = true)
    public Page<Dropbox> page(Pageable pageable) {
        Page<Dropbox> page = repository.findAll(pageable);
        if (!page.isEmpty()) {
            return page;
        }
        return null;
    }
    @Transactional
    public Dropbox refreshToken(Dropbox dropbox) {
        Dropbox entity = new Dropbox();

        entity.setAccessToken(dropbox.getAccessToken());
        entity.setExpirationDate(dropbox.getExpirationDate());
        entity.setRefreshToken(dropbox.getRefreshToken());
        entity.setTokenCreationDate(dropbox.getTokenCreationDate());
        entity.setTokenUpdateDate(dropbox.getTokenUpdateDate());
        entity = repository.save(dropbox);

        return entity;
    }
}
