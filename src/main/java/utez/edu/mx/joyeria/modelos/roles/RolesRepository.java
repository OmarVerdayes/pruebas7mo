package utez.edu.mx.joyeria.modelos.roles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles,Long> {

    boolean existsByDescripcion(String descripcion);
}
