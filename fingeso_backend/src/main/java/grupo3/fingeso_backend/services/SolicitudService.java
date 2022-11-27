package grupo3.fingeso_backend.services;

import grupo3.fingeso_backend.entities.SolicitudEntity;
import grupo3.fingeso_backend.repositories.SolicitudRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class SolicitudService {

    @Autowired
    private final SolicitudRepository solicitudRepository;

    public List<SolicitudEntity> listSolicitudes() { return solicitudRepository.findAll(); }
}
