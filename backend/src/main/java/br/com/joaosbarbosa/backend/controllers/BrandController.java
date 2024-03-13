package br.com.joaosbarbosa.backend.controllers;

import br.com.joaosbarbosa.backend.dto.BrandDTO;
import br.com.joaosbarbosa.backend.entities.Brand;
import br.com.joaosbarbosa.backend.services.BrandService;
import br.com.joaosbarbosa.backend.utils.api.ApiResponseHandler;
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
    public ResponseEntity<ApiResponseHandler> getById(@PathVariable  Long brand_id){
        ApiResponseHandler apiResponseHandler = brandService.getById(brand_id);

        return new ResponseEntity<>(apiResponseHandler,apiResponseHandler.getStatus());
    }

    @GetMapping
    public ResponseEntity<Page<BrandDTO>> page(Pageable pageable){
        Page<BrandDTO> page = brandService.page(pageable);
        return ResponseEntity.ok().body(page);

    }

    @PostMapping
    public ResponseEntity<ApiResponseHandler> insert(@RequestBody BrandDTO dto){
        ApiResponseHandler apiResponseHandler = brandService.insert(dto);
        return new ResponseEntity<>(apiResponseHandler,apiResponseHandler.getStatus());
    }

    @PutMapping("/{brand_id}")
    public ResponseEntity<ApiResponseHandler> update(@RequestBody BrandDTO dto, @PathVariable Long brand_id){
        ApiResponseHandler apiResponseHandler = brandService.update(dto, brand_id);
        return new ResponseEntity<>(apiResponseHandler,apiResponseHandler.getStatus());
    }

    @DeleteMapping("/{brand_id}")
    public ResponseEntity<ApiResponseHandler> delete(@PathVariable Long brand_id){
        ApiResponseHandler apiResponseHandler = brandService.delete(brand_id);
        return new ResponseEntity<>(apiResponseHandler,apiResponseHandler.getStatus());
    }
}
