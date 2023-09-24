package utez.edu.mx.joyeria.modelos.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VentasRepository extends JpaRepository<Ventas,Long> {
    @Modifying
    @Query("UPDATE Ventas v SET v.estatus.id = :estatus WHERE v.id = :id")
    Integer updateEstatusById(@Param("id") Long id, @Param("estatus") Long estatus);

    @Query("SELECT v FROM Ventas v WHERE v.estatus.id = 4 AND v.usuarios.correo=:correo" )
    List<Ventas> findHistoryByCorreo(@Param("correo") String correo);

    @Query("SELECT v FROM Ventas v WHERE v.estatus.id <> 4 AND v.usuarios.correo=:correo" )
    List<Ventas> findInProcess(@Param("correo") String correo);
}
