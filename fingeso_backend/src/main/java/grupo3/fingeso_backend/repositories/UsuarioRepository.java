package grupo3.fingeso_backend.repositories;

import grupo3.fingeso_backend.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Override
    List<Usuario> findAll();
    Usuario findByUsername(String username);
}
