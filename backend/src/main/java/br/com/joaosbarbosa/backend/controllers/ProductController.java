package br.com.joaosbarbosa.backend.controllers;

import br.com.joaosbarbosa.backend.dto.ProductDTO;
import br.com.joaosbarbosa.backend.services.ProductService;
import br.com.joaosbarbosa.backend.utils.api.ApiResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponseHandler> getById(@PathVariable Long productId) {
        ApiResponseHandler apiResponseHandler = productService.getById(productId);
        return new ResponseEntity<>(apiResponseHandler, apiResponseHandler.getStatus());
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> page(Pageable pageable) {
        Page<ProductDTO> productDTOS = productService.page(pageable);
        return ResponseEntity.ok().body(productDTOS);
    }

    @PostMapping
    public ResponseEntity<ApiResponseHandler> insert(@RequestBody ProductDTO source) {
        ApiResponseHandler apiResponseHandler = productService.insert(source);
        return new ResponseEntity<>(apiResponseHandler, apiResponseHandler.getStatus());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponseHandler> update(@RequestBody ProductDTO source, @PathVariable Long productId) {
        ApiResponseHandler apiResponseHandler = productService.update(source, productId);
        return new ResponseEntity<>(apiResponseHandler, apiResponseHandler.getStatus());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseHandler> delete(@PathVariable Long productId) {
        ApiResponseHandler apiResponseHandler = productService.delete(productId);
        return new ResponseEntity<>(apiResponseHandler, apiResponseHandler.getStatus());
    }
}
