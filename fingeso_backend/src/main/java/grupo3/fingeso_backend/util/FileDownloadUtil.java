package grupo3.fingeso_backend.util;

import org.springframework.core.io.UrlResource;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDownloadUtil {
    private Path foundFile;

    public UrlResource getFileAsResource(String fileCode) throws IOException{
        Path dirPath = Paths.get("Files-Upload");

        Files.list(dirPath).forEach(file -> {
            if(file.getFileName().toString().startsWith(fileCode)){
                foundFile = file;
                return;
            }
        });

        if(foundFile != null){
            return new UrlResource(foundFile.toUri());
        }

        return null;
    }
}
