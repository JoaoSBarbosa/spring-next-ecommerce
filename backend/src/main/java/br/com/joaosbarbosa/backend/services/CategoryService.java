package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.CategoryDTO;
import br.com.joaosbarbosa.backend.entities.Category;
import br.com.joaosbarbosa.backend.repositories.CategoryRepository;
import br.com.joaosbarbosa.backend.services.exceptions.ControllerNotFoundException;
import br.com.joaosbarbosa.backend.utils.Util;
import br.com.joaosbarbosa.backend.utils.api.ApiResponseHandler;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponseHandler getById(Long categoryId) {

        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            Category entity = category.get();
            return ApiResponseHandler.builder()
                    .message("Categoria '" + entity.getName() + "' localizada com sucesso!")
                    .status(HttpStatus.OK)
                    .sendDateTime(Util.getDateTime())
                    .object(category)
                    .build();
        }
        return ApiResponseHandler.builder()
                .message("Não localizado com o id informado: '" + categoryId)
                .status(HttpStatus.NOT_FOUND)
                .sendDateTime(Util.getDateTime())
                .object(null)
                .build();

    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> page(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        if (!categories.isEmpty()) {
            return categories.map(CategoryDTO::new);
        }
        return null;
    }

    @Transactional
    public ApiResponseHandler insert(CategoryDTO dto) {
        Category category = new Category(dto.getName(), new Date());
        category = categoryRepository.save(category);
        return ApiResponseHandler.builder()
                .message("Categoria '" + category.getName() + "' inserida com sucesso!")
                .status(HttpStatus.OK)
                .object(category)
                .sendDateTime(Util.getDateTime())
                .build();
    }

    @Transactional
    public ApiResponseHandler update(CategoryDTO dto, Long categoryId) {
        try {
            Category category = categoryRepository.getReferenceById(categoryId);

            category.setName(dto.getName());
            category.setUpdateDate(new Date());

            category = categoryRepository.save(category);
            return ApiResponseHandler.builder()
                    .message("Categoria '" + category.getName() + "' atualizada com sucesso!")
                    .status(HttpStatus.OK)
                    .object(category)
                    .sendDateTime(Util.getDateTime())
                    .build();

        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não localizamos registros de categorias com o id informado! [" + categoryId + "]");
        }
    }

    @Transactional
    public ApiResponseHandler delete(Long categoryId) {

        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(optionalCategory.get().getCategoryId());
            return ApiResponseHandler.builder()
                    .message("Categoria '" + optionalCategory.get().getName() + "' deletada com sucesso!")
                    .status(HttpStatus.OK)
                    .object(optionalCategory)
                    .sendDateTime(Util.getDateTime())
                    .build();
        }
        return ApiResponseHandler.builder()
                .message("Não localizamos registros de categorias com o id informado!" + categoryId)
                .status(HttpStatus.OK)
                .object(optionalCategory)
                .sendDateTime(Util.getDateTime())
                .build();

    }
}
