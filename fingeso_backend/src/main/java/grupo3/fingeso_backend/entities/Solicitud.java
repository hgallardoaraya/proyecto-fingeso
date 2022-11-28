package grupo3.fingeso_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "solicitudes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {
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
    @JsonBackReference(value = "solicitud_usuario")
    private Usuario id_usuario;

    @Column(name = "estado")
    private String estado;

    @Column(name = "resultado_puntuacion")
    private String resultado_puntuacion;

    @Column(name = "resultado_apelacion")
    private String resultado_apelacion;

    @Column(name = "resultado_evaluacion")
    private String resultado_evaluacion;

    @Column(name = "resultado_final")
    private String resultado_final;

    @ManyToOne
    @JoinColumn(name = "id_comite")
    @JsonBackReference("solicitud_comite")
    private Comite comite;

}
