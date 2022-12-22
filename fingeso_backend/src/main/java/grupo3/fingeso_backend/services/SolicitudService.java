package grupo3.fingeso_backend.services;

import grupo3.fingeso_backend.entities.Comite;
import grupo3.fingeso_backend.entities.Documento;
import grupo3.fingeso_backend.entities.Solicitud;
import grupo3.fingeso_backend.repositories.ComiteRepository;
import grupo3.fingeso_backend.repositories.SolicitudRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final ComiteRepository comiteRepository;

    public List<Solicitud> listSolicitudes() { return solicitudRepository.findAll(); }
    public Optional<Solicitud> getSolicitudById(Integer id){
        return solicitudRepository.findById(id);
    }

    public ResponseEntity<?> finalizarPuntuacion(Integer id_solicitud) {
        Solicitud solicitud = solicitudRepository.findById(id_solicitud).get();
        Comite comite = comiteRepository.findById(5).get();
        solicitud.setComite(comite);

        solicitud.setResultado_puntuacion(asignarJerarquia(sumarPuntajes(solicitud.getDocumentos())));
        solicitud.setEstado(comite.getName());
        solicitud.setResultado_final(solicitud.getResultado_puntuacion());

        solicitudRepository.save(solicitud);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Integer sumarPuntajes(List<Documento> documentos){
        Integer sum = 0;
        for(Documento doc : documentos){
            if(doc.getValido()){
                sum += doc.getPuntaje();
            }
        }

        return sum;
    }

    public String asignarJerarquia(Integer puntaje){
        if(puntaje >= 800 && puntaje <= 1000){
            return "Profesor Titular";
        }else if(puntaje >= 600 && puntaje <= 799){
            return "Profesor Asociado";
        }else if(puntaje >= 400 && puntaje <= 599){
            return "Profesor Asistente";
        }else if(puntaje >= 300 && puntaje <= 399){
            return "Instructor";
        }else if(puntaje >= 140 && puntaje <= 299) {
            return "Ayudante";
        }else{
            return "Sin jerarquía definida";
        }
    }
}
