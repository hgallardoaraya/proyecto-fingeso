package grupo3.fingeso_backend.services;
import grupo3.fingeso_backend.entities.UsuarioEntity;
import grupo3.fingeso_backend.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public List<UsuarioEntity> listaUsuarios() { return usuarioRepository.findAll(); }
}

