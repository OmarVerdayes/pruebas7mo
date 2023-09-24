package utez.edu.mx.joyeria.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import utez.edu.mx.joyeria.modelos.estatus.Estatus;
import utez.edu.mx.joyeria.modelos.estatus.EstatusRepository;
import utez.edu.mx.joyeria.extraUtiles.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class EstatusService {
    @Autowired
    EstatusRepository repository;

    @Transactional(readOnly=true)
    public CustomResponse<List<Estatus>> getAll(){
        return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Estatus> insert(@Validated Estatus estatus){
        if(!this.repository.existsByDescripcion(estatus.getDescripcion()))
            return new CustomResponse<>(null, false,200,"Ya existe un estatus registrado con esa descripcion");

        return new CustomResponse<>(this.repository.saveAndFlush(estatus), false,200,"Estatus registrado");
    }
    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Estatus> update(Estatus estatus){

        if(!this.repository.existsById(estatus.getId())){
            return new CustomResponse<>(null,true,400,"El estatus no existe");
        }else if(!this.repository.existsByDescripcion(estatus.getDescripcion())){
            return new CustomResponse<>(null, false,200,"Ya existe un estatus registrado con esa descripcion");
        }

        return new CustomResponse<>(this.repository.saveAndFlush(estatus),false,200,"Estatus actualizado");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Estatus> delete(Long id){
        if(!this.repository.existsById(id))
            return new CustomResponse<>(null,true,400,"El estatus no existe");

        this.repository.deleteById(id);
        return new CustomResponse<>(null,false,200,"Estatus eliminado coreectamente");
    }
}
