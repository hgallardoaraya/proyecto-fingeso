package grupo3.fingeso_backend.entities;

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
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "puntaje_maximo")
    private Integer puntaje_maximo;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "input")
    private Boolean input;

    @Column(name = "img")
    private String img;

    @JsonManagedReference(value="categoria_subcategoria")
    @OneToMany(mappedBy = "categoria")
    private List<SubCategoria> subItems;

}
