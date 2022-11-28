package grupo3.fingeso_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comites")
@Data
public class Comite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "comite")
    private String comite;

    @JsonBackReference(value = "comite_usuario")
    @OneToMany(mappedBy = "comite")
    private List<Usuario> usuarios;

    @JsonManagedReference(value = "solicitud_comite")
    @OneToMany(mappedBy = "comite")
    private List<Solicitud> solicitudes;

}
