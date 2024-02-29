package br.com.joaosbarbosa.backend.controllers;
import br.com.joaosbarbosa.backend.dto.CityDTO;
import br.com.joaosbarbosa.backend.services.CityService;
import br.com.joaosbarbosa.backend.utils.api.ApiResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cities")
@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseHandler> findById(@PathVariable Long id){
        ApiResponseHandler apiResponseHandler = cityService.findById(id);

        return new ResponseEntity<>(apiResponseHandler,apiResponseHandler.getStatus());
    }

    @GetMapping
    public ResponseEntity<Page<CityDTO>> page(Pageable pageablea) {
        Page<CityDTO> dtoPage = cityService.page(pageablea);
        return ResponseEntity.ok().body(dtoPage);
    }

    @PostMapping
    public ResponseEntity<ApiResponseHandler> insert(@RequestBody CityDTO dto){
        ApiResponseHandler apiResponseHandler = cityService.insert(dto);
        return new ResponseEntity<>(apiResponseHandler,apiResponseHandler.getStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseHandler> update(@RequestBody CityDTO dto, @PathVariable Long id){
        ApiResponseHandler apiResponseHandler = cityService.update(dto, id);
        return new ResponseEntity<>(apiResponseHandler,apiResponseHandler.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseHandler> delete(@PathVariable Long id){
        ApiResponseHandler apiResponseHandler = cityService.delete(id);
        return new ResponseEntity<>(apiResponseHandler,apiResponseHandler.getStatus());
    }
}
