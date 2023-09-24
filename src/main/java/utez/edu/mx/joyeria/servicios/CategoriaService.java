package utez.edu.mx.joyeria.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import utez.edu.mx.joyeria.modelos.categoria.Categoria;
import utez.edu.mx.joyeria.modelos.categoria.CategoriaRepository;
import utez.edu.mx.joyeria.extraUtiles.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository repository;

    @Transactional(readOnly=true)
    public CustomResponse<List<Categoria>> getAll(){
        return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Categoria> insert(@Validated Categoria categoria){
        if(!this.repository.existsByDescripcion(categoria.getDescripcion()))
            return new CustomResponse<>(null, false,200,"Ya existe una categoria registrada con esa descripcion");

        return new CustomResponse<>(this.repository.saveAndFlush(categoria), false,200,"Categoria registrada!");
    }
    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Categoria> update(Categoria categoria){

        if(!this.repository.existsById(categoria.getId())){
            return new CustomResponse<>(null,true,400,"La categoria no existe");
        }else if(!this.repository.existsByDescripcion(categoria.getDescripcion())){
            return new CustomResponse<>(null, false,200,"Ya existe una categoria registrada con esa descripcion");
        }

        return new CustomResponse<>(this.repository.saveAndFlush(categoria),false,200,"Categoria actualizada");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Categoria> delete(Long id){
        if(!this.repository.existsById(id))
            return new CustomResponse<>(null,true,400,"La categoria no existe");

        this.repository.deleteById(id);
        return new CustomResponse<>(null,false,200,"Categoria eliminada coreectamente");
    }
}
