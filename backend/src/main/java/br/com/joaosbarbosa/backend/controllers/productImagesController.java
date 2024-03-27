package br.com.joaosbarbosa.backend.controllers;

import br.com.joaosbarbosa.backend.dto.ProductImagesDTO;
import br.com.joaosbarbosa.backend.services.ProductImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products_images")
public class productImagesController {

    @Autowired
    ProductImagesService productImagesService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductImagesDTO> getById(@PathVariable Long id) {
        ProductImagesDTO productImagesDTO = productImagesService.getById(id);
        return ResponseEntity.ok().body(productImagesDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductImagesDTO>> page(Pageable pageable) {
        Page<ProductImagesDTO> page = productImagesService.page(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ProductImagesDTO> insert(
            @PathVariable Long productId,
            @RequestParam("file") MultipartFile file) {
        ProductImagesDTO productImagesDTO = productImagesService.insert(productId, file);
        return ResponseEntity.ok().body(productImagesDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productImagesService.deleteImage(id);
        return null;
    }
}
