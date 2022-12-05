package grupo3.fingeso_backend.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil{
    public static void saveFile(String uploadPathName, String fileName, MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get("Files-Upload/" + uploadPathName);
        if(Files.exists(uploadPath)){
            System.out.println(uploadPathName);
            FileUtils.cleanDirectory(new File(uploadPath.toUri()));
        }
        else{
            Files.createDirectories(uploadPath);
        }

        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException ioe){
            throw new IOException("Could not save the file " + fileName, ioe);
        }
    }
}