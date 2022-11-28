package grupo3.fingeso_backend.controllers;

import grupo3.fingeso_backend.entities.SolicitudEntity;
import grupo3.fingeso_backend.entities.UsuarioEntity;
import grupo3.fingeso_backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:8000")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/usuario/getAllUsuarios")
    public List<UsuarioEntity> listaUsuarios(){ return usuarioService.listaUsuarios(); }

    @GetMapping("/usuario/getSolicitudesByUser/{id}")
    public List<SolicitudEntity> getSolicitudesByUser(@PathVariable(value = "id") Integer id){
        return usuarioService.getSolicitudesByUserId(id);
    };

    @GetMapping("/usuario/getUserById/{id}")
    public Optional<UsuarioEntity> getUserById(@PathVariable(value = "id") Integer id){
        return usuarioService.getUserById(id);
    };
}
