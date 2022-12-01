package grupo3.fingeso_backend.services;

import grupo3.fingeso_backend.entities.Categoria;
import grupo3.fingeso_backend.repositories.CategoriaRepository;
import grupo3.fingeso_backend.repositories.ComiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> getCategorias(){
        return categoriaRepository.findAll();
    }

}
