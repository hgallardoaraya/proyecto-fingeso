package grupo3.fingeso_backend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "respaldos_solicitudes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Respaldo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer puntaje;

    private boolean valido;

    private String archivo;

    @ManyToOne
    @JoinColumn(name = "id_actividad")
    @JsonBackReference(value="respaldo-actividad")
    private Actividad actividad;

    @ManyToOne
    @JoinColumn(name = "id_solicitud")
    @JsonBackReference(value="respaldo-solicitud")
    private Solicitud solicitud;

}
