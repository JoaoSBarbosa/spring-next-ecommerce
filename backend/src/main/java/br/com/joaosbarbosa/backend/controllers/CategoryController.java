package br.com.joaosbarbosa.backend.controllers;

import br.com.joaosbarbosa.backend.dto.CategoryDTO;
import br.com.joaosbarbosa.backend.services.CategoryService;
import br.com.joaosbarbosa.backend.utils.api.ApiResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{categories}")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponseHandler> getById(@PathVariable Long categoryId) {
        ApiResponseHandler apiResponseHandler = categoryService.getById(categoryId);
        return new ResponseEntity<>(apiResponseHandler, apiResponseHandler.getStatus());
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> page(Pageable pageable) {
        Page<CategoryDTO> page = categoryService.page(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping
    public ResponseEntity<ApiResponseHandler> insert(@RequestBody CategoryDTO dto) {
        ApiResponseHandler apiResponseHandler = categoryService.insert(dto);
        return new ResponseEntity<>(apiResponseHandler, apiResponseHandler.getStatus());
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponseHandler> update(@PathVariable Long categoryId, @RequestBody CategoryDTO dto) {
        ApiResponseHandler apiResponseHandler = categoryService.update(dto, categoryId);
        return new ResponseEntity<>(apiResponseHandler, apiResponseHandler.getStatus());
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponseHandler> delete(@PathVariable Long categoryId) {
        ApiResponseHandler apiResponseHandler = categoryService.delete(categoryId);
        return new ResponseEntity<>(apiResponseHandler, apiResponseHandler.getStatus());
    }
}
