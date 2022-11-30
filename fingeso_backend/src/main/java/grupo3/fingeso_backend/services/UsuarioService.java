package grupo3.fingeso_backend.services;
import grupo3.fingeso_backend.entities.Usuario;
import grupo3.fingeso_backend.repositories.ComiteRepository;
import grupo3.fingeso_backend.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final ComiteRepository comiteRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsername(username);
        if(user == null){
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }else{
            log.info("User found");
        }
        System.out.println(passwordEncoder.encode(user.getPassword()));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getComite().getName()));
        System.out.println(authorities);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public List<Usuario> listaUsuarios() { return usuarioRepository.findAll(); }

    public Optional<Usuario> getUserById(Integer id){
        System.out.println(usuarioRepository.findById(id).get().getCargo());
        return usuarioRepository.findById(id);
    }

    public Usuario getUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}

