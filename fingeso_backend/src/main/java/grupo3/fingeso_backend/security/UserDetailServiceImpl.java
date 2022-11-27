package grupo3.fingeso_backend.security;

import grupo3.fingeso_backend.entities.UsuarioEntity;
import grupo3.fingeso_backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException{
        System.out.println("el correo es " + correo);
        UsuarioEntity usuarioEntity = usuarioRepository
                .findOneByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + correo + " no existe."));
        System.out.println("el correo es " + correo);
        return new UserDetailsImpl(usuarioEntity);
    }

}
