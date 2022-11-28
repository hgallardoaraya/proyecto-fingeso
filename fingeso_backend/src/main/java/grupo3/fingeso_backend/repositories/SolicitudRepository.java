package grupo3.fingeso_backend.repositories;

import grupo3.fingeso_backend.entities.Solicitud;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends CrudRepository<Solicitud, Integer> {

    @Override
    List<Solicitud> findAll();
}
