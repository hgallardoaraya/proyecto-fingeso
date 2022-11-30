package grupo3.fingeso_backend.entities;

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
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "rut")
    private String rut;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "facultad")
    private String facultad;

    @Column(name = "desempenio")
    private Integer desempenio;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "jerarquia")
    private String jerarquia;

    @Column(name = "antiguedad")
    private Date antiguedad;

    @Column(name = "ultima_jerarquia")
    private Date ultima_jerarquia;

    @JsonManagedReference(value="solicitud_usuario")
    @OneToMany(mappedBy = "id_usuario")
    private List<Solicitud> solicitudes;

    @JsonManagedReference(value = "comite_usuario")
    @ManyToOne
    @JoinColumn(name = "id_comite")
    private Comite comite;

}
