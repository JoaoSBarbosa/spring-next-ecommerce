package br.com.joaosbarbosa.backend.services;
import br.com.joaosbarbosa.backend.dto.StateDTO;
import br.com.joaosbarbosa.backend.entities.State;
import br.com.joaosbarbosa.backend.repositories.StateRepository;
import br.com.joaosbarbosa.backend.services.exceptions.ControllerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    StateRepository stateRepository;

    @Transactional(readOnly = true)
    public StateDTO findById(Long id) {
        Optional<State> stateOptional = stateRepository.findById(id);
        if (!stateOptional.isPresent()) {
           
        	throw new ControllerNotFoundException("Não existe registro de estado com o id informando");

        }
        return new StateDTO(stateOptional.get());

    }

    @Transactional(readOnly = true)
    public Page<StateDTO> page(PageRequest pageRequest) {
        Page<State> states = stateRepository.findAll(pageRequest);

        if (!states.isEmpty()) {
            return states.map(StateDTO::new);
        }

        return null;
    }

    @Transactional
    public StateDTO insert(StateDTO dto) {
        Optional<State> stateOptional = stateRepository.findByName(dto.getName());
        if (stateOptional.isPresent()) {

        	throw new ControllerNotFoundException("O estado" + dto.getName() + " já está cadstrado no sistema");
        }
        State state = new State();
        copyData(dto, state);
        state = stateRepository.save(state);

        return new StateDTO(state);
    }

    @Transactional
    public StateDTO update(StateDTO dto, Long id) {
        Optional<State> stateOptional = stateRepository.findById(id);
        if (!stateOptional.isPresent()) {
           throw new ControllerNotFoundException("Não existe nenhum registro de estado com o id informando" + id + ". Por favor, tente novamente!");
        }
        State state = stateOptional.get();

        state.setAcronym(dto.getAcronym());
        state.setName(dto.getName());
        state.setUpdateDate(new Date());

        state = stateRepository.save(state);
        return new StateDTO(state);


    }

    @Transactional
    public StateDTO delete(Long id) {
        State stateOptional = stateRepository.findById(id).orElse(null);

        if (stateOptional != null) {
            stateRepository.delete(stateOptional);
            return null;

        } else {
           throw new ControllerNotFoundException("Não existe nenhum registro de estado com o id informado " + id + ". Por favor, tente novamente!");
        }
    }

    private void copyData(StateDTO source, State insert) {
        insert.setName(source.getName());
        insert.setAcronym(source.getAcronym());
        insert.setCreationDate(new Date());
    }

}
