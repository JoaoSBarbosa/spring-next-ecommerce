package br.com.joaosbarbosa.backend.controllers;

import br.com.joaosbarbosa.backend.dto.StateDTO;
import br.com.joaosbarbosa.backend.entities.State;
import br.com.joaosbarbosa.backend.services.StateService;
import br.com.joaosbarbosa.backend.utils.api.ApiResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/states")
@RestController
public class StateController {
    @Autowired
    StateService stateService;

    @GetMapping("/{id}")
    public ResponseEntity<StateDTO> findById(@PathVariable Long id){
        StateDTO stateDTO = stateService.findById(id);

        return  ResponseEntity.ok().body(stateDTO);
    }

    @GetMapping
    public ResponseEntity<Page<StateDTO>> page(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "20") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy

    )
    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<StateDTO> stateDTOS = stateService.page(pageRequest);
        return ResponseEntity.ok().body(stateDTOS);

    }

    @PostMapping
    public ResponseEntity<ApiResponseHandler> insert(@RequestBody StateDTO stateDTO){
        ApiResponseHandler apiResponseHandler = stateService.insert(stateDTO);
        return new ResponseEntity<>(apiResponseHandler,apiResponseHandler.getStatus());
    }
}
