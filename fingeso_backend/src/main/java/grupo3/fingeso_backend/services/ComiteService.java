package grupo3.fingeso_backend.services;

import grupo3.fingeso_backend.entities.Comite;
import grupo3.fingeso_backend.entities.Solicitud;
import grupo3.fingeso_backend.repositories.ComiteRepository;
import grupo3.fingeso_backend.repositories.SolicitudRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ComiteService {

    private final ComiteRepository comiteRepository;

    public List<Comite> getAllComites(){
        return comiteRepository.findAll();
    }
}
