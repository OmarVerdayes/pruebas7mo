package utez.edu.mx.joyeria.modelos.productos;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductosRepository extends JpaRepository<Productos,Long> {
    @Query("SELECT p FROM Productos p WHERE p.estatus = 1")
    List<Productos> findActivos();

    @Transactional
    @Modifying
    @Query("UPDATE Productos pro SET pro.estatus = :estatus WHERE pro.id = :id")
    Productos updateEstatusById( @Param("id") Long id,@Param("estatus") Long estatus);

}
