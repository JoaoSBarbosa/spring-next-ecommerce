package br.com.joaosbarbosa.backend.controllers;

import br.com.joaosbarbosa.backend.dto.BrandDTO;
import br.com.joaosbarbosa.backend.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping("/{brand_id}")
    public ResponseEntity<BrandDTO> getById(@PathVariable  Long brand_id){
    	BrandDTO brandDTO = brandService.getById(brand_id);

        return ResponseEntity.ok().body(brandDTO);
    }

    @GetMapping
    public ResponseEntity<Page<BrandDTO>> page(Pageable pageable){
        Page<BrandDTO> page = brandService.page(pageable);
        return ResponseEntity.ok().body(page);

    }

    @PostMapping
    public ResponseEntity<BrandDTO> insert(@RequestBody BrandDTO dto){
        BrandDTO brandDTO = brandService.insert(dto);
        return ResponseEntity.ok().body(brandDTO);
    }

    @PutMapping("/{brand_id}")
    public ResponseEntity<BrandDTO> update(@RequestBody BrandDTO dto, @PathVariable Long brand_id){
        BrandDTO brandDTO = brandService.update(dto, brand_id);
        return ResponseEntity.ok().body(brandDTO);
    }

    @DeleteMapping("/{brand_id}")
    public void delete(@PathVariable Long brand_id){
       brandService.delete(brand_id);
    }
}
