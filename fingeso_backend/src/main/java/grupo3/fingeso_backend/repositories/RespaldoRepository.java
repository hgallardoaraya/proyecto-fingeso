package grupo3.fingeso_backend.repositories;

import grupo3.fingeso_backend.entities.Respaldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RespaldoRepository extends JpaRepository<Respaldo, Integer> {

    @Override
    Optional<Respaldo> findById(Integer id);

}