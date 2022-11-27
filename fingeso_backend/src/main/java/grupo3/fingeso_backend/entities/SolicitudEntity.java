package grupo3.fingeso_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "solicitud")
@Data

public class SolicitudEntity {
    @Id
    private Integer id;
    private Integer puntuacion;
    private Date fecha_envio;
    private Integer id_usuario;
}
