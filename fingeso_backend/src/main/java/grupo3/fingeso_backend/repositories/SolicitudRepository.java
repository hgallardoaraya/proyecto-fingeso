package grupo3.fingeso_backend.repositories;

import grupo3.fingeso_backend.entities.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

    @Override
    List<Solicitud> findAll();
}
