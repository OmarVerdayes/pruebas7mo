package utez.edu.mx.joyeria.modelos.carrito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utez.edu.mx.joyeria.modelos.productos.Productos;

import java.util.List;

public interface CarritoRepository extends JpaRepository<Carrito,Long> {

    @Query("SELECT c.productos FROM Carrito c WHERE c.usuarios.correo = :correo")
    List<Productos> findByUsuariosCorreo(@Param("correo") String correo);

    @Query("DELETE FROM Carrito car WHERE car.usuarios.correo = :correo")
    void deleteByUsuario(@Param("correo") String correo);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Carrito c WHERE c.usuarios.correo = :correo AND c.productos.id = :idProducto")
    boolean existsByCorreoAndIdProducto(@Param("correo") String correo, @Param("idProducto") Long idProducto);

    void deleteByUsuariosCorreoAndProductosId(String correo, Long idProducto);

}
