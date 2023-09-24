package utez.edu.mx.joyeria.modelos.productos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.joyeria.modelos.categoria.Categoria;

@Entity
@Table(name="productos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "varchar(100)",nullable = false)
    private String nombre;
    @Column( columnDefinition = "varchar(255)",nullable = false)
    private String descripcion;
    @Column( columnDefinition = "int",nullable = false)
    private Long stock;
    @Column( columnDefinition = "int",nullable = false)
    private Long estatus;
    @Column( columnDefinition = "text",nullable = false)
    private String foto;
    @Column( columnDefinition = "double",nullable = false)
    private Long precio;
    @ManyToOne
    @JoinColumn(name="id_categoria",nullable = false)
    private Categoria categoria;
}
