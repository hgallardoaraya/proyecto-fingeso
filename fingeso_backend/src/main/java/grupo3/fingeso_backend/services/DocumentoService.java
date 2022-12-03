package grupo3.fingeso_backend.services;

import grupo3.fingeso_backend.controllers.FileUploadResponse;
import grupo3.fingeso_backend.entities.Actividad;
import grupo3.fingeso_backend.entities.Documento;
import grupo3.fingeso_backend.entities.Respaldo;
import grupo3.fingeso_backend.entities.SubCategoria;
import grupo3.fingeso_backend.repositories.DocumentoRepository;
import grupo3.fingeso_backend.repositories.GrupoActividadesRepository;
import grupo3.fingeso_backend.repositories.SubCategoriaRepository;
import grupo3.fingeso_backend.util.FileUploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DocumentoService {
    private final DocumentoRepository documentoRepository;
    private final GrupoActividadesRepository grupoActividadesRepository;
    private final SubCategoriaRepository subCategoriaRepository;

    public ResponseEntity<FileUploadResponse> uploadFile(MultipartFile multipartFile,
                                                         Integer id,
                                                         String tipoRespaldo,
                                                         Integer idActividad,
                                                         Integer puntaje,
                                                         Boolean valido,
                                                         Integer idSolicitud)
        throws IOException {
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                long size = multipartFile.getSize();
                Documento documento = new Documento();
                if(!documentoRepository.existsById(id)){
                    documento.setArchivo(multipartFile.getBytes());
                    documento.setTipoRespaldo(tipoRespaldo);
                    if(tipoRespaldo.equals("actividad")){
                        documentoRepository.findById(id);
                    }else if(tipoRespaldo.equals("grupoActividades")){

                    }else if(tipoRespaldo.equals("subCategoria")){

                    }

                }
                documentoRepository.save(documento);

                FileUploadUtil.saveFile(fileName, multipartFile);
                FileUploadResponse response = new FileUploadResponse();
                response.setFileName(fileName);
                response.setSize(size);
                response.setDownloadUri("/downloadFile/" + fileName);
                return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
