package grupo3.fingeso_backend.repositories;

import grupo3.fingeso_backend.entities.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

}
