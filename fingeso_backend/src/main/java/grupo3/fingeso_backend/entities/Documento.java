package grupo3.fingeso_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "documentos")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "archivo")
    private String archivo;

    @Column(name = "tipo_respaldo")
    private String tipoRespaldo;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria")
    private SubCategoria subCategoria;

    @ManyToOne
    @JoinColumn(name = "id_grupoactividades")
    private GrupoActividades grupoActividades;

    @ManyToOne
    @JoinColumn(name = "id_actividad")
    private Actividad actividad;

    @ManyToOne
    @JoinColumn(name = "id_solicitud")
    @JsonBackReference(value = "solicitud_documentos")
    private Solicitud solicitud;

    @Column(name = "puntaje")
    private Integer puntaje;

    @Column(name = "valido")
    private Boolean valido;
}
