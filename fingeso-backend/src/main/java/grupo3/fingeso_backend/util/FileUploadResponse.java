package grupo3.fingeso_backend.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FileUploadResponse {
    private String fileName;
    private String downloadUri;
    private Integer id;
}
