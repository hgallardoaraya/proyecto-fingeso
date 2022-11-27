package grupo3.fingeso_backend.repositories;

import grupo3.fingeso_backend.entities.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Integer> {
    @Override
    List<UsuarioEntity> findAll();

    Optional<UsuarioEntity> findOneByCorreo(String correo);
}
