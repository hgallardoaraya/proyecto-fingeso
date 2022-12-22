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
        foundFile = Paths.get("Files-Upload/" + fileCode);

        System.out.println("filename " + foundFile.getFileName());

        if(foundFile != null){
            return new UrlResource(foundFile.toUri());
        }

        return null;
    }
}
