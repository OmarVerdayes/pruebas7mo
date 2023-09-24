package utez.edu.mx.joyeria.modelos.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.joyeria.modelos.roles.Roles;

@Entity
@Table(name="usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuarios {
    @Id
    @Column( columnDefinition = "varchar(150)",unique = true,nullable = false)
    private String correo;
    @Column( columnDefinition = "text",nullable = false)
    private String contrasena;
    @Column( columnDefinition = "varchar(45)",nullable = false)
    private String nombre;
    @Column( columnDefinition = "varchar(45)",nullable = false)
    private String apellido_paterno;
    @Column( columnDefinition = "varchar(45)",nullable = false)
    private String apellido_materno;
    @Column( columnDefinition = "int default 0",nullable = false)
    private Long estatus;
    @Column( columnDefinition = "varchar(150)")
    private String direccion;
    @ManyToOne
    @JoinColumn(name = "id_rol",nullable = false)
    private Roles rol;
}
