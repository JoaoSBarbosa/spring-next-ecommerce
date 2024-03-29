package br.com.joaosbarbosa.backend.controllers;

import br.com.joaosbarbosa.backend.dto.CategoryDTO;
import br.com.joaosbarbosa.backend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long categoryId) {
        CategoryDTO categoryDTO = categoryService.getById(categoryId);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> page(Pageable pageable) {
        Page<CategoryDTO> page = categoryService.page(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto) {
        CategoryDTO categoryDTO = categoryService.insert(dto);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long categoryId, @RequestBody CategoryDTO dto) {
        CategoryDTO categoryDTO = categoryService.update(dto, categoryId);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long categoryId) {
        CategoryDTO categoryDTO = categoryService.delete(categoryId);
        return ResponseEntity.ok().body(categoryDTO);
    }
}
