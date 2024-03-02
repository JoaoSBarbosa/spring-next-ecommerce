package br.com.joaosbarbosa.backend.controllers;

import br.com.joaosbarbosa.backend.dto.BrandDTO;
import br.com.joaosbarbosa.backend.services.BrandService;
import br.com.joaosbarbosa.backend.utils.api.ApiResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    BrandService brandService;


    @PostMapping
    public ResponseEntity<ApiResponseHandler> insert(@RequestBody BrandDTO dto){
        ApiResponseHandler apiResponseHandler = brandService.insert(dto);
        return new ResponseEntity<>(apiResponseHandler,apiResponseHandler.getStatus());
    }
}
