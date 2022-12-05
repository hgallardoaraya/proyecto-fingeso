package grupo3.fingeso_backend.services;

import grupo3.fingeso_backend.controllers.FileUploadResponse;
import grupo3.fingeso_backend.entities.*;
import grupo3.fingeso_backend.repositories.*;
import grupo3.fingeso_backend.util.FileUploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class DocumentoService {
    private final DocumentoRepository documentoRepository;
    private final ActividadRepository actividadRepository;
    private final GrupoActividadesRepository grupoActividadesRepository;
    private final SubCategoriaRepository subCategoriaRepository;
    private final SolicitudRepository solicitudRepository;

    public ResponseEntity<FileUploadResponse> uploadFile(MultipartFile multipartFile,
                                                         Integer idDocumento,
                                                         String tipoRespaldo,
                                                         Integer idTipo,
                                                         Integer idSolicitud)
        throws IOException {
                long size = multipartFile.getSize();
                Documento documento = null;
                Solicitud solicitud = solicitudRepository.findById(idSolicitud).get();
                String uploadPathName = solicitud.getUsuario().getUsername()
                        + "/" + tipoRespaldo + "/" + String.valueOf(idTipo);
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

                if(idDocumento == -1){
                    documento = new Documento();
                    documento.setTipoRespaldo(tipoRespaldo);

                    if(tipoRespaldo.equals("actividad")){
                        Actividad actividad = actividadRepository.findById(idTipo).get();
                        documento.setActividad(actividad);
                    }else if(tipoRespaldo.equals("grupoActividades")){
                        GrupoActividades grupoActividades = grupoActividadesRepository.findById(idTipo).get();
                        documento.setGrupoActividades(grupoActividades);
                    }else if(tipoRespaldo.equals("subCategoria")){
                        SubCategoria subCategoria = subCategoriaRepository.findById(idTipo).get();
                        documento.setSubCategoria(subCategoria);
                    }

                    documento.setSolicitud(solicitud);
                    documento.setPuntaje(0);
                    documento.setValido(false);
                }else if(!documentoRepository.existsById(idDocumento)){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }else if(documentoRepository.existsById(idDocumento)){
                    documento = documentoRepository.findById(idDocumento).get();
                }

                documento.setArchivo(uploadPathName + "/" + fileName);
                documentoRepository.save(documento);

                FileUploadUtil.saveFile(uploadPathName, fileName, multipartFile);
                FileUploadResponse response = new FileUploadResponse();
                response.setFileName(fileName);
                response.setSize(size);
                response.setDownloadUri("/downloadFile/" + uploadPathName + "/" + fileName);
                return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
