package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.entities.Person;
import br.com.joaosbarbosa.backend.repositories.PersonRepository;
import br.com.joaosbarbosa.backend.services.exceptions.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PersonManagementService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    EmailService emailService;

    @Transactional
    public String getCode(String email) {
        try {
            Person person = personRepository.findByEmail(email);

            person.setCodeSendDate(new Date());
            person.setPasswordRecoveryCode(generateRecoveryCode(person.getPersonId()));
            personRepository.saveAndFlush(person);
            emailService.sendEmail(person.getEmail(), "Código Recuperação de Senha", "O seu código para recuperação de senha é: " + person.getPasswordRecoveryCode());
            return "Código enviado";
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não foi encontrado registros no sistema com o e-mail informado: " + email);
        }
    }

    public String generateRecoveryCode(Long id) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmssmm");
        return dateFormat.format(new Date()) + id;
    }


}
