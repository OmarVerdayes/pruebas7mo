package utez.edu.mx.joyeria.modelos.ventas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.joyeria.modelos.estatus.Estatus;
import utez.edu.mx.joyeria.modelos.productos.Productos;
import utez.edu.mx.joyeria.modelos.usuarios.Usuarios;

@Entity
@Table(name="ventas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "date",nullable = false)
    private String fecha_compra;
    @Column( columnDefinition = "date")
    private String fecha_entrega;
    @Column( columnDefinition = "double",nullable = false)
    private double precio;
    @Column( columnDefinition = "int",nullable = false)
    private Long piezas;
    @ManyToOne
    @JoinColumn(name = "estatus",nullable = false)
    private Estatus estatus;
    @ManyToOne
    @JoinColumn(name = "correo",nullable = false)
    private Usuarios usuarios;
    @ManyToOne
    @JoinColumn(name = "id_producto",nullable = false)
    private Productos productos;


}
