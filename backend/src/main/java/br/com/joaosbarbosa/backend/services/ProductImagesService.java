package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.dto.ProductImagesDTO;
import br.com.joaosbarbosa.backend.entities.Product;
import br.com.joaosbarbosa.backend.entities.ProductImages;
import br.com.joaosbarbosa.backend.repositories.ProductImagesRepository;
import br.com.joaosbarbosa.backend.repositories.ProductRepository;
import br.com.joaosbarbosa.backend.services.dropboxApiServices.ApiDropboxServiceUpload;
import br.com.joaosbarbosa.backend.services.exceptions.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class ProductImagesService {

    private static final String UPLOAD_DIR = "D:\\Workspace\\WorkspaceJava\\upload\\wolfEcommerce";

    @Autowired
    ProductImagesRepository productImagesRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ApiDropboxServiceUpload apiDropboxServiceUpload;

    @Transactional(readOnly = true)
    public ProductImagesDTO getById(Long id) {
        ProductImages productImages = productImagesRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Não localizamos registros de Imagens de produto com o id informado"));
        return new ProductImagesDTO(productImages);
    }

    @Transactional(readOnly = true)
    public Page<ProductImagesDTO> page(Pageable pageable) {
        Page<ProductImages> productImages = productImagesRepository.findAll(pageable);
        if (!productImages.isEmpty()) {
            return productImages.map(ProductImagesDTO::new);
        }
        return null;
    }

    @Transactional
    public ProductImagesDTO insert(Long productId, MultipartFile file) {

        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + productId));

            ProductImages productImages = new ProductImages();
            productImages.setCreationDate(new Date());
            productImages.setProduct(product);

            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes(); // pegando o arquivo em byte
                String productName = product.getShortDescription();
                String imageName = String.valueOf("_" + productName) + "_" + file.getOriginalFilename();

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
                productImages.setUriImageDropbox(uriImageDropbox);
                productImages.setName(imageName);

            }
            productImages = productImagesRepository.saveAndFlush(productImages);

            productRepository.saveAndFlush(product);

            return new ProductImagesDTO(productImages);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não localizamos registros de Imagens de produto com o id informado " + productId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteImage(Long imageId){
        ProductImages productImages = productImagesRepository.findById(imageId).orElseThrow(()-> new ControllerNotFoundException("Imagem do produto não encontrada com ID:" + imageId));

        if(productImages.getUriImageDropbox() != null)  {
            apiDropboxServiceUpload.deleteImage(productImages.getUriImageDropbox());
        }

        try{
            Files.deleteIfExists(Paths.get(productImages.getUriImage()));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao excluir imagem local: " + e.getMessage(), e);
        }
        productImagesRepository.deleteById(imageId);

    }


//    @Transactional
//    public ProductImagesDTO insert(Long productId, MultipartFile file) {
//
//        try {
//            Product product = productRepository.getReferenceById(productId);
//            ProductImages productImages = new ProductImages();
//
//            productImages.setCreationDate(new Date());
//            productImages.setProduct(product);
//
//            if(!file.isEmpty()){
//                byte[] bytes = file.getBytes(); // pegando o arquivo em byte
//                String imageName = String.valueOf(product.getShortDescription()) + file.getOriginalFilename();
//                //definindo o caminho da imagem
//                Path path = Paths.get(UPLOAD_DIR + imageName);
//                //gravar a imagem no caminho
//                Files.write(path, bytes);
//
//                productImages.setUriImage(String.valueOf(path));
//                productImages.setName(imageName);
//
//            }
//            productImages = productImagesRepository.saveAndFlush(productImages);
//            product.getImages().add(productImages);
//            productRepository.saveAndFlush(product);
//
//            return new ProductImagesDTO(productImages);
//        } catch (EntityNotFoundException e) {
//
//            throw new ControllerNotFoundException("Não localizamos registros de Imagens de produto com o id informado " + productId);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    @Transactional
    public ProductImagesDTO update(ProductImagesDTO source, Long id) {

        try {
            ProductImages productImages = productImagesRepository.getReferenceById(id);
            productImages.setUpdateDate(new Date());

            productImagesRepository.save(productImages);
            return new ProductImagesDTO(productImages);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não localizamos registros de Imagens de produto com o id informado " + id);
        }
    }
}
