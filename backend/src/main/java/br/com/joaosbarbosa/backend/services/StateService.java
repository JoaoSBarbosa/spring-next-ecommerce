package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.StateDTO;
import br.com.joaosbarbosa.backend.entities.State;
import br.com.joaosbarbosa.backend.repositories.StateRepository;
import br.com.joaosbarbosa.backend.utils.Util;
import br.com.joaosbarbosa.backend.utils.api.ApiResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if(stateOptional.isPresent()){
            return ApiResponseHandler.builder()
                    .message("O estado "+dto.getName()+" já está cadstrado no sistema")
                    .sendDateTime(Util.getDateTime())
                    .status(HttpStatus.BAD_REQUEST)
                    .object(null)
                    .build();
        }
        State state = new State(dto.getName(), dto.getAcronym(), dto.getCreationDate(),dto.getUpdateDate());
        state = stateRepository.save(state);

        return ApiResponseHandler.builder()
                .message("O estado "+dto.getName()+" jregistrado com sucesso")
                .sendDateTime(Util.getDateTime())
                .status(HttpStatus.OK)
                .object(state)
                .build();
    }
}
