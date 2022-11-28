package grupo3.fingeso_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "solicitudes")
@Data
public class SolicitudEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "puntuacion")
    private Integer puntuacion;

    @Column(name = "fecha_envio")
    private Date fecha_envio;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonBackReference
    private UsuarioEntity id_usuario;


}
