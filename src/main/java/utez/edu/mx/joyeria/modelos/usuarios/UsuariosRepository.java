package utez.edu.mx.joyeria.modelos.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios,Long> {

    @Query("SELECT u FROM Usuarios u WHERE u.correo = :correo")
    Optional<Usuarios> getOneByCorreo(@Param("correo") String correo);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuarios u WHERE u.correo = :correo")
    boolean existsByCorreo(@Param("correo") String correo);


    @Modifying
    @Query("UPDATE Usuarios u SET u.estatus = :estatus WHERE u.correo = :correo")
    Usuarios updateEstatusById(@Param("estatus") Long estatus, @Param("correo") String correo);




}
