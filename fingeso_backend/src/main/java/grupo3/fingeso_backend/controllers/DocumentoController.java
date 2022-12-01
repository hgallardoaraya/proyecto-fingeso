package grupo3.fingeso_backend.controllers;

import grupo3.fingeso_backend.entities.Respaldo;
import grupo3.fingeso_backend.entities.Solicitud;
import grupo3.fingeso_backend.services.RespaldoService;
import grupo3.fingeso_backend.services.SolicitudService;
import grupo3.fingeso_backend.util.FileDownloadUtil;
import grupo3.fingeso_backend.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DocumentoController {

    @Autowired
    SolicitudService solicitudService;
    @Autowired
    RespaldoService respaldoService;

    @PostMapping("/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam("file") MultipartFile multipartFile
//            @RequestParam("id") Integer id
    )
        throws IOException{
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
        String fileCode = FileUploadUtil.saveFile(fileName, multipartFile);

//        System.out.println(id);
//        respaldoService.updateFileRespaldoById(id, fileName);

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri("/downloadFile/" + fileCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) throws IOException {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        UrlResource resource = null;

        try{
            resource = downloadUtil.getFileAsResource(fileCode);
        }catch(IOException e){
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        if(resource == null){
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource + "\"";
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

    @GetMapping("/respaldo/getRespaldoById")
    public Optional<Respaldo> getRespaldoById(@RequestHeader Integer id_solicitud){
        return respaldoService.getRespaldoById(id_solicitud);
    };

    @GetMapping("/respaldo/updateFileRespaldoById")
    public Optional<Respaldo> updateFileRespaldoById(@RequestHeader Integer id, @RequestHeader String fileName){
        return respaldoService.updateFileRespaldoById(id, fileName);
    };

}
