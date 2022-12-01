package grupo3.fingeso_backend.services;

import grupo3.fingeso_backend.entities.Respaldo;
import grupo3.fingeso_backend.repositories.RespaldoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RespaldoService {

    private final RespaldoRepository respaldoRepository;

    public Optional<Respaldo> getRespaldoById(Integer id_solicitud) {
        return respaldoRepository.findById(id_solicitud);
    }

    public Optional<Respaldo> updateFileRespaldoById(Integer id, String fileName) {
        Optional<Respaldo> opRespaldo = respaldoRepository.findById(id);
        Respaldo respaldo = opRespaldo.get();
        respaldo.setArchivo(fileName);

        return opRespaldo;
    }
}