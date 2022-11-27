package grupo3.fingeso_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {
    @Id
    private Integer id;
    private String email;
    private String password;
    private String nombre;
    private String rut;
    private String comite;
    private String departamento;
    private String facultad;
    private Integer desempenio;
    private String cargo;
    private String jerarquia;
    private Date antiguedad;
    private Date ultima_jerarquia;

}
