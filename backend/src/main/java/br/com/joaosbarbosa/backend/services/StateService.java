package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.StateDTO;
import br.com.joaosbarbosa.backend.entities.State;
import br.com.joaosbarbosa.backend.repositories.StateRepository;
import br.com.joaosbarbosa.backend.utils.Util;
import br.com.joaosbarbosa.backend.utils.api.ApiResponseHandler;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    StateRepository stateRepository;

    public StateDTO findById(Long id) {
        Optional<State> stateOptional = stateRepository.findById(id);
        if (stateOptional.isPresent()) {
            return new StateDTO(stateOptional.get());
        }
        return null;
    }

    @Transactional(readOnly = true)
    public Page<StateDTO> page(PageRequest pageRequest) {
        Page<State> states = stateRepository.findAll(pageRequest);

        if (!states.isEmpty()) {
            return states.map(StateDTO::new);
        }
        return null;
    }

    public ApiResponseHandler insert(StateDTO dto) {
        Optional<State> stateOptional = stateRepository.findByName(dto.getName());
        if (stateOptional.isPresent()) {
            return ApiResponseHandler.builder()
                    .message("O estado " + dto.getName() + " já está cadstrado no sistema")
                    .sendDateTime(Util.getDateTime())
                    .status(HttpStatus.BAD_REQUEST)
                    .object(null)
                    .build();
        }
        State state = new State(dto.getName(), dto.getAcronym(), dto.getCreationDate(), dto.getUpdateDate());
        state = stateRepository.save(state);

        return ApiResponseHandler.builder()
                .message("O estado " + dto.getName() + " jregistrado com sucesso")
                .sendDateTime(Util.getDateTime())
                .status(HttpStatus.OK)
                .object(state)
                .build();
    }

    public ApiResponseHandler update(StateDTO dto, Long id) {
        Optional<State> stateOptional = stateRepository.findById(id);
        if (!stateOptional.isPresent()) {
            return ApiResponseHandler.builder()
                    .message("Não existe nenhum registro de estado com o id informando" + id + ". Por favor, tente novamente!")
                    .status(HttpStatus.BAD_REQUEST)
                    .sendDateTime(Util.getDateTime())
                    .object(null)
                    .build();
        }
        State state = stateOptional.get();

        state.setAcronym(dto.getAcronym());
        state.setName(dto.getName());
        state.setUpdateDate(new Date());

        state = stateRepository.save(state);
        return ApiResponseHandler.builder()
                .message("Estado" + state.getName() + " atualizado com sucesso!")
                .status(HttpStatus.OK)
                .sendDateTime(Util.getDateTime())
                .object(state)
                .build();


    }

    public ApiResponseHandler delete(Long id) {
        State stateOptional = stateRepository.findById(id).orElse(null);

        if (stateOptional != null) {
            stateRepository.delete(stateOptional);

            return ApiResponseHandler.builder()
                    .message("Estado " + stateOptional.getName() + " deletado com sucesso!")
                    .status(HttpStatus.OK)
                    .sendDateTime(Util.getDateTime())
                    .object(null)
                    .build();
        } else {
            return ApiResponseHandler.builder()
                    .message("Não existe nenhum registro de estado com o id informado " + id + ". Por favor, tente novamente!")
                    .status(HttpStatus.BAD_REQUEST)
                    .sendDateTime(Util.getDateTime())
                    .object(null)
                    .build();
        }
    }

}