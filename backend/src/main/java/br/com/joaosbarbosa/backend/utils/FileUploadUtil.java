package br.com.joaosbarbosa.backend.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException{
        File directory = new File(uploadDir);
        System.out.println("CHEGOU EM saveFile");
        if(!directory.exists()){
            directory.mkdirs();
        }

        String filePath = uploadDir + File.separator+fileName;
        File dest = new File(filePath);
        multipartFile.transferTo(dest);
    }
}
