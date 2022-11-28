package grupo3.fingeso_backend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "rut")
    private String rut;

    @Column(name = "comite")
    private String comite;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "id_usuario")
    List<SolicitudEntity> solicitudes;

}
