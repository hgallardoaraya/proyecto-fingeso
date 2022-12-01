package grupo3.fingeso_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "solicitudes")
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

    @Column(name = "periodo")
    private Date periodo;

    @ManyToOne
    @JoinColumn(name = "id_comite")
    @JsonBackReference(value="solicitud_comite")
    private Comite comite;

    @JsonManagedReference(value="respaldo-solicitud")
    @OneToMany(mappedBy = "solicitud")
    private List<Respaldo> respaldos;

}
