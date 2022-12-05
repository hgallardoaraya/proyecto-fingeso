package grupo3.fingeso_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grupos_actividades")
public class GrupoActividades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "puntaje_maximo")
    private Integer puntaje_maximo;

    @Column(name = "input")
    private Boolean input;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria")
    @JsonBackReference(value = "subcategoria_grupoactividades")
    private SubCategoria subCategoria;

    @JsonManagedReference(value = "grupoactividades_actividad")
    @OneToMany(mappedBy = "grupoActividades")
    private List<Actividad> subItems;

    @Transient
    private String tipo = "grupoActividades";
}

