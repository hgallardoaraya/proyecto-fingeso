package grupo3.fingeso_backend.controllers;

import grupo3.fingeso_backend.entities.UsuarioEntity;
import grupo3.fingeso_backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/usuario/getAllUsuarios")
    public List<UsuarioEntity> listaUsuarios(){ return usuarioService.listaUsuarios(); }


}
