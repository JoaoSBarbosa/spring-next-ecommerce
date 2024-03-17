package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.ProductDTO;
import br.com.joaosbarbosa.backend.entities.Brand;
import br.com.joaosbarbosa.backend.entities.Category;
import br.com.joaosbarbosa.backend.entities.Product;
import br.com.joaosbarbosa.backend.repositories.BrandRepository;
import br.com.joaosbarbosa.backend.repositories.CategoryRepository;
import br.com.joaosbarbosa.backend.repositories.ProductRepository;
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
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public ProductDTO getById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            return null;
        }
        return new ProductDTO(productOptional.get());
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> page(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        if (!products.isEmpty()) {
            return products.map(ProductDTO::new);
        }
        return null;
    }

    @Transactional
    public ProductDTO insert(ProductDTO source) {
        Product product = new Product();
        copyDtoToEntity(source, product, false);
        product = productRepository.save(product);
        return new ProductDTO(product);
    }


    @Transactional
    public ProductDTO update(ProductDTO source, Long productId) {
        Product productDescriptions = productRepository.getByShortAndDetailsDescription(source.getShortDescription(), source.getDetailedDescription());
        if (productDescriptions != null) {
            throw new ControllerNotFoundException("Já existe um registro de produto com a descrição curta '" + productDescriptions.getShortDescription() + " e descição longa '" + productDescriptions.getDetailedDescription() + "' informada!");
        }
        try {
            Product product = productRepository.getReferenceById(productId);
            copyDtoToEntity(source, product, true);
            product = productRepository.save(product);

            return new ProductDTO(product);

        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não localizamos registros de produtos com o id informado");
        }
    }

    @Transactional
    public void delete(Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            productRepository.deleteById(product.get().getProductId());
        }

    }

 

    private void copyDtoToEntity(ProductDTO source, Product entity, Boolean isUpdate) {

        if (isUpdate) {
            entity.setUpdateDate(new Date());
        } else {
            entity.setCreationDate(new Date());
        }
        if (source.getBrand() != null) {
            Brand brand = brandRepository.findById(source.getBrand().getBrandId()).orElseThrow(() -> new ControllerNotFoundException("Nenhuma marca encontrada com o ID informado: " + source.getBrand().getBrandId()));
            entity.setBrand(brand);
        }
        if (source.getCategory() != null) {
            Category category = categoryRepository.findById(source.getCategory().getCategoryId()).orElseThrow(() -> new ControllerNotFoundException("Categoria não encontrada com o ID informado: " + source.getCategory().getCategoryId()));
            entity.setCategory(category);
        }
        if (source.getSaleValue() != null) entity.setSaleValue(source.getSaleValue());
        if (source.getShortDescription() != null) entity.setShortDescription(source.getShortDescription());
        if (source.getDetailedDescription() != null) entity.setDetailedDescription(source.getDetailedDescription());
        if (source.getValueCost() != null) entity.setValueCost(source.getValueCost());

    }

}
