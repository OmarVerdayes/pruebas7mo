package utez.edu.mx.joyeria.modelos.estatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstatusRepository extends JpaRepository<Estatus, Long> {


    @Query("SELECT CASE WHEN COUNT(est) > 0 THEN true ELSE false END FROM Estatus est WHERE est.descripcion = :descripcion")
    boolean existsByDescripcion(@Param("descripcion") String descripcion);

}
