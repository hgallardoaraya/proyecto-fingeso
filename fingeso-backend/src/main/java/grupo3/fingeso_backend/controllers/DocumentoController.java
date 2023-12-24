package grupo3.fingeso_backend.controllers;

import grupo3.fingeso_backend.services.DocumentoService;
import grupo3.fingeso_backend.services.SolicitudService;
import grupo3.fingeso_backend.util.FileDownloadUtil;
import grupo3.fingeso_backend.util.FileUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class DocumentoController {

    @Autowired
    DocumentoService documentoService;

    @Autowired
    SolicitudService solicitudService;

    @PostMapping("/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("id_documento") Integer id_documento,
            @RequestParam("tipo_respaldo") String tipo_respaldo,
            @RequestParam("id_tipo") Integer id_tipo,
            @RequestParam("id_solicitud") Integer id_solicitud
    ) throws IOException {
        return documentoService.uploadFile(multipartFile, id_documento, tipo_respaldo, id_tipo, id_solicitud);
    }

    @GetMapping("/downloadFile/{username}/{tipo}/{idTipo}/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable("username") String username,
                                          @PathVariable("tipo") String tipo,
                                          @PathVariable("idTipo") String idTipo,
                                          @PathVariable("fileName") String fileName) throws IOException {

        return documentoService.downloadFile(username, tipo, idTipo, fileName);
    }

    @GetMapping("/documento/{tipo}/{idTipo}")
    public ResponseEntity<FileUploadResponse> getDocumentoByTipo(@PathVariable("tipo") String tipo,
                                                                 @PathVariable("idTipo") Integer idTipo){
        return documentoService.getDocumentoByTipo(tipo, idTipo);
    }

    @PostMapping("/documento/addPuntaje")
    public ResponseEntity<?> addPuntaje(
            @RequestParam("id_documento") Integer id_documento,
            @RequestParam("puntaje") Integer puntaje,
            @RequestParam("valido") Boolean valido
    ) throws IOException {
        return documentoService.addPuntaje(id_documento, puntaje, valido);
    }
}
