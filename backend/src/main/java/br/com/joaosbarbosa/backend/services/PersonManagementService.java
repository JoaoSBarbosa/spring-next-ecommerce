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

    //    public String changePassword(Person person) {
//        try {
//            System.out.println(person);
//            Person entity = personRepository.findByEmailAndPasswordRecoveryCode(person.getEmail(), person.getPasswordRecoveryCode());
//
//            if (entity == null) return "Não existe registro no sistema com esse e-mail ou código informado";
//
//            // data em milisegundos
//            Date difference = new Date(new Date().getTime() - entity.getCodeSendDate().getTime());
//            if (difference.getTime() / 1000 >= 900) {
//
//                System.out.println("CHGEOU NO IF DE difference.getTime() / 1000 >= 900");
//                entity.setPassword(person.getPassword());
//                entity.setPasswordRecoveryCode(null);
//                personRepository.saveAndFlush(entity);
//
//                emailService.sendEmail(entity.getEmail(), "Alteração de senha", "Tempo expirado. Solicite um novo codigo");
//                return "Tempo expirado. Solicite um novo codigo";
//
//            } else {
//                System.out.println("NÃO ENTRUPU NO IF");
//
//                emailService.sendEmail(entity.getEmail(), "Alteração de senha", "Tempo expirado. Solicite um novo codigo");
//                return "Tempo expirado. Solicite um novo codigo";
//            }
//
//
//        } catch (Exception e) {
//            System.out.println("Ocorreu um erro: " + e);
//            return "Ocorreu um erro: " + e;
//        }
//    }
    public String changePassword(Person person) {
        try {
            System.out.println(person);
            Person entity = personRepository.findByEmailAndPasswordRecoveryCode(person.getEmail(), person.getPasswordRecoveryCode());

            if (entity == null) {
                return "Não existe registro no sistema com esse e-mail ou código informado";
            }

            // data em milisegundos
            Date difference = new Date(new Date().getTime() - entity.getCodeSendDate().getTime());
            if (difference.getTime() / 1000 >= 900) {
                System.out.println("CHGEOU NO IF DE difference.getTime() / 1000 >= 900");
                emailService.sendEmail(entity.getEmail(), "Alteração de senha", "Tempo expirado. Solicite um novo código");
                return "Tempo expirado. Solicite um novo código";
            } else {
                System.out.println("NÃO ENTROU NO IF");
                entity.setPassword(person.getPassword());
                entity.setPasswordRecoveryCode(null);
                personRepository.saveAndFlush(entity);
                emailService.sendEmailChangePassword(entity.getEmail(), entity.getFirstName(), "Senha alterada com sucesso!");
                return "Senha alterada com sucesso!";
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e);
            return "Ocorreu um erro: " + e;
        }
    }

    public String generateRecoveryCode(Long id) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmssmm");
        return dateFormat.format(new Date()) + id;
    }


}
