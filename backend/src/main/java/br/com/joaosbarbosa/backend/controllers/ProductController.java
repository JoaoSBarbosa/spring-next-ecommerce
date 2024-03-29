package br.com.joaosbarbosa.backend.controllers;

import br.com.joaosbarbosa.backend.dto.ProductDTO;
import br.com.joaosbarbosa.backend.services.ProductService;
import com.dropbox.core.DbxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long productId) {
        ProductDTO productDTO = productService.getById(productId);
        return ResponseEntity.ok().body(productDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> page(Pageable pageable) {
        Page<ProductDTO> productDTOS = productService.page(pageable);
        return ResponseEntity.ok().body(productDTOS);
    }

    //	@PostMapping
//	public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO source) throws IOException, DbxException {
//		ProductDTO productDTO = productService.insert(source);
//		return ResponseEntity.ok().body(productDTO);
//	}
    @PostMapping
    public ResponseEntity<ProductDTO> insert(
            @RequestPart("source") ProductDTO source,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException, DbxException {
        System.out.println("VALOR DE source.getShortDescription() EM CONTROLLER: " + source.getShortDescription());
        ProductDTO productDTO = productService.insert(source, file);
        return ResponseEntity.ok().body(productDTO);
    }


    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestPart("file") MultipartFile file
    ) throws IOException, DbxException {
        String file1 = productService.upload(file);
        return ResponseEntity.ok().body(file1);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO source, @PathVariable Long productId) {
        ProductDTO productDTO = productService.update(source, productId);
        return ResponseEntity.ok().body(productDTO);
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable Long productId) {
        productService.delete(productId);
    }
}
