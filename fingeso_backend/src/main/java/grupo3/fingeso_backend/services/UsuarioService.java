package grupo3.fingeso_backend.services;
import grupo3.fingeso_backend.entities.Solicitud;
import grupo3.fingeso_backend.entities.Usuario;
import grupo3.fingeso_backend.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public List<Usuario> listaUsuarios() { return usuarioRepository.findAll(); }

//    public List<Solicitud> getSolicitudesByUserId(Integer id){
//        Optional<Usuario> usuario = usuarioRepository.findById(id);
//        return usuario.get().getSolicitudes();
//    }

    public Optional<Usuario> getUserById(Integer id){
        System.out.println(usuarioRepository.findById(id).get().getCargo());
        return usuarioRepository.findById(id);
    }
}

