package utez.edu.mx.joyeria.modelos.estatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="estatus")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Estatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "varchar(100)",nullable = false)
    private String descripcion;
}
