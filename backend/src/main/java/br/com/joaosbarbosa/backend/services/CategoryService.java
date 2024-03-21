package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.CategoryDTO;
import br.com.joaosbarbosa.backend.entities.Category;
import br.com.joaosbarbosa.backend.repositories.CategoryRepository;
import br.com.joaosbarbosa.backend.services.exceptions.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public CategoryDTO getById(Long categoryId) {

		Optional<Category> category = categoryRepository.findById(categoryId);

		if (!category.isPresent()) {
			throw new ControllerNotFoundException("Não localizado com o id informado: '" + categoryId);
		}

		Category entity = category.get();
		return new CategoryDTO(entity);

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
	public CategoryDTO insert(CategoryDTO dto) {
		Category category = new Category(dto.getName(), new Date());
		category = categoryRepository.save(category);
		return new CategoryDTO(category);

	}

	@Transactional
	public CategoryDTO update(CategoryDTO dto, Long categoryId) {
		try {
			Category category = categoryRepository.getReferenceById(categoryId);

			category.setName(dto.getName());
			category.setUpdateDate(new Date());

			category = categoryRepository.save(category);
			return new CategoryDTO(category);

		} catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException(
					"Não localizamos registros de categorias com o id informado! [" + categoryId + "]");
		}
	}

	@Transactional
	public CategoryDTO delete(Long categoryId) {

		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

		if (!optionalCategory.isPresent()) {
			throw new ControllerNotFoundException(
					"Não localizamos registros de categorias com o id informado!" + categoryId);
		}

		categoryRepository.deleteById(optionalCategory.get().getCategoryId());
		
		return null;

	}
}
