package utez.edu.mx.joyeria.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import utez.edu.mx.joyeria.modelos.roles.Roles;
import utez.edu.mx.joyeria.modelos.roles.RolesRepository;
import utez.edu.mx.joyeria.extraUtiles.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class RolesService {
    @Autowired
    RolesRepository repository;

    @Transactional(readOnly=true)
    public CustomResponse<List<Roles>> getAll(){
        return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Roles> insert(@Validated Roles roles){
        if(!this.repository.existsByDescripcion(roles.getDescripcion()))
            return new CustomResponse<>(null, false,200,"Ya existe un rol registrado con esa descripcion");

        return new CustomResponse<>(this.repository.saveAndFlush(roles), false,200,"Rol registrado");
    }
    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Roles> update(Roles roles){

        if(!this.repository.existsById(roles.getId())){
            return new CustomResponse<>(null,true,400,"El rol no existe");
        }else if(!this.repository.existsByDescripcion(roles.getDescripcion())){
            return new CustomResponse<>(null, false,200,"Ya existe un rol registrado con esa descripcion");
        }

        return new CustomResponse<>(this.repository.saveAndFlush(roles),false,200,"Rol actualizado");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Roles> delete(Long id){
        if(!this.repository.existsById(id))
            return new CustomResponse<>(null,true,400,"El rol no existe");

        this.repository.deleteById(id);
        return new CustomResponse<>(null,false,200,"Estatus eliminado coreectamente");
    }
}
