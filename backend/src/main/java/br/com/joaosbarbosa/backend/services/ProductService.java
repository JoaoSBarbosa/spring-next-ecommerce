package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.ProductDTO;
import br.com.joaosbarbosa.backend.dto.ProductImagesDTO;
import br.com.joaosbarbosa.backend.entities.Brand;
import br.com.joaosbarbosa.backend.entities.Category;
import br.com.joaosbarbosa.backend.entities.Product;
import br.com.joaosbarbosa.backend.entities.ProductImages;
import br.com.joaosbarbosa.backend.repositories.BrandRepository;
import br.com.joaosbarbosa.backend.repositories.CategoryRepository;
import br.com.joaosbarbosa.backend.repositories.ProductImagesRepository;
import br.com.joaosbarbosa.backend.repositories.ProductRepository;
import br.com.joaosbarbosa.backend.services.dropboxApiServices.ApiDropboxServiceUpload;
import br.com.joaosbarbosa.backend.services.exceptions.ControllerNotFoundException;
import br.com.joaosbarbosa.backend.utils.FileUploadUtil;
import com.dropbox.core.DbxException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    private static final String UPLOAD_DIR = "D:\\Workspace\\WorkspaceJava\\upload\\wolfEcommerce";

    @Autowired
    ProductRepository productRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductImagesRepository productImagesRepository;
    @Autowired
    private ApiDropboxServiceUpload apiDropboxServiceUpload;

    @Transactional(readOnly = true)
    public ProductDTO getById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            return null;
        }
//        return new ProductDTO(productOptional.get(), productOptional.get().getProductImages());
        return new ProductDTO(productOptional.get());
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> page(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        if (!products.isEmpty()) {
//            return products.map(product -> new ProductDTO(product, product.getProductImages()));
            return products.map(ProductDTO::new);
        }
        return null;
    }

    @Transactional
    public ProductDTO insert(ProductDTO source, MultipartFile file) throws IOException, DbxException {
        Product product = new Product();
        copyDtoToEntity(source, product, file, false);

        product = productRepository.save(product);

        if (file != null) {
            byte[] bytes = file.getBytes(); // pegando o arquivo em byte

            String productName = source.getShortDescription();
            String imageName = String.valueOf("_" + productName) + "_" + file.getOriginalFilename();

            ProductImages productImages = new ProductImages();
            //Verifica se a pasta do produto existe, senão, cria
            String productFolderPath = Paths.get(UPLOAD_DIR, productName).toString();
            Path localPath = Paths.get(productFolderPath);

            if(!Files.exists(localPath)){
                Files.createDirectories(localPath);
            }
            //definindo caminho no dropbox
            String uriImageDropbox = apiDropboxServiceUpload.uploadImage(productName, bytes, imageName);

            Path imagePath = Paths.get(productFolderPath,imageName);
            Files.write(imagePath, bytes);

            productImages.setUriImage(imagePath.toString());
            productImages.setProduct(product);
            productImages.setCreationDate(new Date());

            productImages.setUriImageDropbox(uriImageDropbox);
            productImages.setName(imageName);

            productImagesRepository.saveAndFlush(productImages);
        }
        return new ProductDTO(product);
    }

    private void copyDtoToEntity(ProductDTO source, Product entity, MultipartFile file, Boolean isUpdate) throws IOException {
        if (isUpdate) {
            entity.setUpdateDate(new Date());
        } else {
            entity.setCreationDate(new Date());
        }
        if (source.getBrand() != null) {
            Brand brand = brandRepository.findById(source.getBrand().getBrandId())
                    .orElseThrow(() -> new ControllerNotFoundException(
                            "Nenhuma marca encontrada com o ID informado: " + source.getBrand().getBrandId()));
            entity.setBrand(brand);
        }
        if (source.getCategory() != null) {
            Category category = categoryRepository.findById(source.getCategory().getCategoryId())
                    .orElseThrow(() -> new ControllerNotFoundException(
                            "Categoria não encontrada com o ID informado: " + source.getCategory().getCategoryId()));
            entity.setCategory(category);
        }
        if (source.getSaleValue() != null) {
            entity.setSaleValue(source.getSaleValue());
        }
        if (source.getShortDescription() != null) {
            entity.setShortDescription(source.getShortDescription());
        }
        if (source.getDetailedDescription() != null) {
            entity.setDetailedDescription(source.getDetailedDescription());
        }
        if (source.getValueCost() != null) {
            entity.setValueCost(source.getValueCost());
        }
        System.out.println("TESTE O VALOR DE getShortDescription FORA DE file É:  "+source.getShortDescription());



    }


    @Transactional
    public ProductDTO update(ProductDTO source, Long productId) {
        Product productDescriptions = productRepository.getByShortAndDetailsDescription(source.getShortDescription(),
                source.getDetailedDescription());
        if (productDescriptions != null) {
            throw new ControllerNotFoundException("Já existe um registro de produto com a descrição curta '"
                    + productDescriptions.getShortDescription() + " e descição longa '"
                    + productDescriptions.getDetailedDescription() + "' informada!");
        }
        try {
            Product product = productRepository.getReferenceById(productId);
//            copyDtoToEntity(source, product, true);
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


    public String upload(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uploadDir = "D:\\Workspace\\WorkspaceJava\\upload\\wolfEcommerce";
        String filePath = uploadDir + "/" + fileName;

        FileUploadUtil.saveFile(uploadDir, fileName, file);

        // 5. Salvar a URL e o nome da imagem na entidade ProductImages
//		ProductImages productImage = new ProductImages();
//		productImage.setName(fileName);
//		productImage.setUriImage(filePath);
//		productImagesRepository.save(productImage);

        return filePath;
    }
}
