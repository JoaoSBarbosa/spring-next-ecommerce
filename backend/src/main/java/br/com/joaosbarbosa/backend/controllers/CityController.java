package br.com.joaosbarbosa.backend.controllers;
import br.com.joaosbarbosa.backend.dto.CityDTO;
import br.com.joaosbarbosa.backend.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cities")
@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> findById(@PathVariable Long id){
        CityDTO cityDTO = cityService.findById(id);

        return ResponseEntity.ok().body(cityDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CityDTO>> page(Pageable pageablea) {
        Page<CityDTO> dtoPage = cityService.page(pageablea);
        return ResponseEntity.ok().body(dtoPage);
    }

    @PostMapping
    public ResponseEntity<CityDTO> insert(@RequestBody CityDTO dto){
        CityDTO cityDTO = cityService.insert(dto);
        return ResponseEntity.ok().body(cityDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> update(@RequestBody CityDTO dto, @PathVariable Long id){
        CityDTO cityDTO = cityService.update(dto, id);
        return ResponseEntity.ok().body(cityDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CityDTO> delete(@PathVariable Long id){
        CityDTO cityDTO = cityService.delete(id);
        return ResponseEntity.ok().body(cityDTO);
    }
}
