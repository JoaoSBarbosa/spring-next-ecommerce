package br.com.joaosbarbosa.backend.services.dropboxApiServices;

import br.com.joaosbarbosa.backend.entities.Dropbox;
import br.com.joaosbarbosa.backend.repositories.DropboxRepository;
import br.com.joaosbarbosa.backend.services.DropboxService;
import br.com.joaosbarbosa.backend.services.exceptions.ControllerNotFoundException;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DeleteErrorException;
import com.dropbox.core.v2.files.WriteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ApiDropboxServiceUpload {
    @Autowired
    DropboxRepository dropboxRepository;

    public String uploadImage(String productName, byte[] imageData, String imageName) {
        try (InputStream inputStream = new ByteArrayInputStream(imageData)) {
            Dropbox dropbox = dropboxRepository.findById(1L).orElseThrow(() -> new ControllerNotFoundException("Não existe registro de Dropbox com ID 1"));
            DbxRequestConfig config = DbxRequestConfig.newBuilder("application-ecommerce-dropbox").build();

            DbxClientV2 clientV2 = new DbxClientV2(config, dropbox.getAccessToken());

            String remotePath = "/" + productName + "/" + imageName;
            clientV2.files().uploadBuilder(remotePath)
                    .withMode(WriteMode.ADD)
                    .uploadAndFinish(inputStream);
            System.out.println("Imagem enviada com sucesso para o Dropbox: " + remotePath);
            return remotePath; // Retorna o caminho da imagem
        } catch (IOException | DbxException e) {
            throw new RuntimeException("Erro ao enviar imagem para o Dropbox: " + e.getMessage(), e);
        }
    }

    public void deleteImage(String imagePath) {

        try {
            Dropbox dropbox = dropboxRepository.findById(1L)
                    .orElseThrow(() -> new ControllerNotFoundException("Não existe registro de Dropbox com ID 1"));

            DbxRequestConfig config = DbxRequestConfig.newBuilder("application-ecommerce-dropbox").build();
            DbxClientV2 clientV2 = new DbxClientV2(config, dropbox.getAccessToken());

            clientV2.files().deleteV2(imagePath);
            System.out.println("Imagem deletada com sucesso do Dropbox: " + imagePath);
        } catch (DeleteErrorException e) {
            System.out.println("Erro ao deletar imagem do Dropbox: " + e.getMessage());
        } catch (DbxException e) {
            throw new RuntimeException(e);
        }

    }
}