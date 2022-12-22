package grupo3.fingeso_backend.services;

import grupo3.fingeso_backend.util.FileDownloadUtil;
import grupo3.fingeso_backend.util.FileUploadResponse;
import grupo3.fingeso_backend.entities.*;
import grupo3.fingeso_backend.repositories.*;
import grupo3.fingeso_backend.util.FileUploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
                response.setDownloadUri("/downloadFile/" + uploadPathName + "/" + fileName);
                response.setId(documento.getId());
                return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> downloadFile(String username, String tipo, String idTipo, String fileName){
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        UrlResource resource = null;

        String filePath = username + "/" + tipo + "/" + idTipo + "/" + fileName;

        try{
            resource = downloadUtil.getFileAsResource(filePath);
        }catch(IOException e){
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        if(resource == null){
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
        String filExtension = resource.getFilename().substring(resource.getFilename().lastIndexOf('.'));
        System.out.println(resource.getFilename().substring(resource.getFilename().lastIndexOf('.')));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, headerValue);
        headers.add("FileExtension", filExtension);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .headers(headers)
                .body(resource);
    }

    public ResponseEntity<FileUploadResponse> getDocumentoByTipo(String tipo, Integer idTipo) {
        FileUploadResponse response = new FileUploadResponse();

        List<Documento> documentos = documentoRepository.findAll();

        File file = null;
        Boolean flag = false;
        for(Documento doc : documentos){
            if(tipo.equals("actividad")
                    && doc.getTipoRespaldo().equals(tipo)
                    && doc.getActividad().getId().equals(idTipo)){
                flag = true;
            }
            else if(tipo.equals("grupoActividades")
                    && doc.getTipoRespaldo().equals(tipo)
                    && doc.getGrupoActividades().getId().equals(idTipo)){
                flag = true;
            }
            else if(tipo.equals("subCategoria")
                    && doc.getTipoRespaldo().equals(tipo)
                    && doc.getSubCategoria().getId().equals(idTipo)){
                flag = true;
            }

            if(flag){
                file = new File(doc.getArchivo());
                response.setFileName(file.getName());
                response.setDownloadUri("/downloadFile/" + doc.getArchivo());
                response.setId(doc.getId());
                break;
            }
        }

        if(!flag){
            response.setFileName("null");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> addPuntaje(Integer id_documento, Integer puntaje, Boolean valido) {

        Documento documento = documentoRepository.findById(id_documento).get();

        documento.setPuntaje(puntaje);
        documento.setValido(valido);

        documentoRepository.save(documento);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
