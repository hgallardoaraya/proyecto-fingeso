package grupo3.fingeso_backend.repositories;

import grupo3.fingeso_backend.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
