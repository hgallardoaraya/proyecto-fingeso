package grupo3.fingeso_backend.controllers;


import grupo3.fingeso_backend.entities.Comite;
import grupo3.fingeso_backend.services.ComiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ComiteController {

    @Autowired
    ComiteService comiteService;

    @GetMapping("/comite/getAllComites")
    public List<Comite> getAllComites(){
        return comiteService.getAllComites();
    }
}
