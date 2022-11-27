package grupo3.fingeso_backend.repositories;

import grupo3.fingeso_backend.entities.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Integer> {
    @Override
    List<UsuarioEntity> findAll();
}
