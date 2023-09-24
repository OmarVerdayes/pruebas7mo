package utez.edu.mx.joyeria.modelos.carrito;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.joyeria.modelos.productos.Productos;
import utez.edu.mx.joyeria.modelos.usuarios.Usuarios;

@Entity
@Table(name="carrito")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "correo",nullable = false)
    private Usuarios usuarios;
    @ManyToOne
    @JoinColumn(name = "id_producto",nullable = false)
    private Productos productos;

}
