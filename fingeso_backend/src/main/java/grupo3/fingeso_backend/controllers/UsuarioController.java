package grupo3.fingeso_backend.controllers;

import grupo3.fingeso_backend.entities.Solicitud;
import grupo3.fingeso_backend.entities.Usuario;
import grupo3.fingeso_backend.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/usuario/getAllUsuarios")
    public List<Usuario> listaUsuarios(){ return usuarioService.listaUsuarios(); }

    @GetMapping("/usuario/getUserById/{id}")
    public Optional<Usuario> getUserById(@PathVariable(value = "id") Integer id){
        return usuarioService.getUserById(id);
    };

    @GetMapping("/usuario")
    public ResponseEntity<Usuario> getUsuario(@RequestHeader String username){
        System.out.println(username);
        return ResponseEntity.ok().body(usuarioService.getUsuarioByUsername(username));
    }

    @GetMapping("/usuario/solicitudes")
    public ResponseEntity<List<Solicitud>> getSolicitudesUsuario(@RequestHeader String username){
            return ResponseEntity.ok().body(usuarioService.getSolicitudesUsuario(username));
    }
}
