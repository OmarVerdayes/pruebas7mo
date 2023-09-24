package utez.edu.mx.joyeria.modelos.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query("SELECT COUNT(cat) > 0 FROM Categoria cat WHERE cat.descripcion = :descripcion")
    boolean existsByDescripcion(@Param("descripcion") String descripcion);
}
