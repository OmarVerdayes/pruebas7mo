package utez.edu.mx.joyeria.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import utez.edu.mx.joyeria.modelos.productos.Productos;
import utez.edu.mx.joyeria.modelos.productos.ProductosRepository;
import utez.edu.mx.joyeria.extraUtiles.ActionsFiles;
import utez.edu.mx.joyeria.extraUtiles.CustomResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class ProductosService {
    @Autowired
    ProductosRepository repository;

    ActionsFiles actions=new ActionsFiles();
    private String ruta="archivos//productos//";

    @Transactional(readOnly=true)
    public CustomResponse<List<Productos>> getAll(){
        return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
    }

    @Transactional(readOnly = true)
    public CustomResponse<Productos> getOne(Long id){
        return new CustomResponse<>(this.repository.findById(id).get(),false,200,"OK");
    }

    @Transactional(readOnly=true)
    public CustomResponse<List<Productos>> getActivos(){
        return new CustomResponse<>(this.repository.findActivos(),false,200, "OK");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Productos> insert(@Validated Productos productos, MultipartFile fotoArchivo) throws IOException {

        productos.setFoto(actions.saveFile(fotoArchivo,ruta));
        return new CustomResponse<>(this.repository.saveAndFlush(productos), false,200,"Producto registrado");
    }

    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Productos> update(Productos productos, MultipartFile fotoArchivo) throws IOException{
        if(!this.repository.existsById(productos.getId()))
            return new CustomResponse<>(null,true,400,"El producto no existe");

        Productos pro=this.repository.findById(productos.getId()).get();
        if (fotoArchivo==null){
            productos.setFoto(pro.getFoto());
        }else {
            actions.deleteFile(pro.getFoto());
            productos.setFoto(actions.saveFile(fotoArchivo,ruta));
        }

        return new CustomResponse<>(this.repository.saveAndFlush(productos),false,200,"Producto actualizado");
    }

    @Transactional
    public CustomResponse<Productos> patchEstatus(Long id, Long nuevoEstatus) {
        if (!repository.existsById(id)) {
            return new CustomResponse<>(null, true, 400, "El producto no existe");
        }

        return new CustomResponse<>(this.repository.updateEstatusById(id, nuevoEstatus), false, 200, "Estatus actualizado");
    }


    /*@Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Productos> delete(Long id){
        if(!this.repository.existsById(id))
            return new CustomResponse<>(null,true,400,"El producto no existe");
        this.repository.deleteById(id);
        return new CustomResponse<>(null,false,200,"producto eliminado coreectamente");
    }*/


}
