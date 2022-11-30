package grupo3.fingeso_backend.controllers;


import grupo3.fingeso_backend.entities.Solicitud;
import grupo3.fingeso_backend.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SolicitudController {

    @Autowired
    SolicitudService solicitudService;

    @GetMapping("/solicitud/getAllSolicitudes")
    public List<Solicitud> getAllSolicitudes(){
        return solicitudService.listSolicitudes();
    }
}
