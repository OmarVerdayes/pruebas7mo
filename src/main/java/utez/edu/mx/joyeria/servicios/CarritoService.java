package utez.edu.mx.joyeria.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import utez.edu.mx.joyeria.modelos.carrito.Carrito;
import utez.edu.mx.joyeria.modelos.carrito.CarritoRepository;
import utez.edu.mx.joyeria.modelos.productos.Productos;
import utez.edu.mx.joyeria.extraUtiles.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class CarritoService {

    @Autowired
    CarritoRepository repository;

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Carrito> insert(@Validated Carrito carrito){
        if(this.repository.existsByCorreoAndIdProducto(carrito.getUsuarios().getCorreo(),carrito.getProductos().getId())){
            System.out.println("Entra en que si existe el producto en el carrito-----------------------------------------------------------");
            this.repository.deleteByUsuariosCorreoAndProductosId(carrito.getUsuarios().getCorreo(),carrito.getProductos().getId());
            return new CustomResponse<>(null, false,200,"El producto se ha eliminado de su carrito");
        }
        return new CustomResponse<>(this.repository.saveAndFlush(carrito), false,200,"Producto añadido al carrito");
    }


    /*@Transactional(readOnly = true)
    public CustomResponse<List<Productos>> getByUsuario(String correo){
        return new CustomResponse<>(this.repository.findProductsInCartByUsuario(correo),false,200,"OK");
    }*/
    @Transactional(readOnly = true)
    public CustomResponse<List<Productos>> getByUsuario(String correo){



        return new CustomResponse<>(this.repository.findByUsuariosCorreo(correo),false,200,"OK");
    }


    @Transactional(readOnly = true)
    public CustomResponse<List<Carrito>> getAll(){
        return new CustomResponse<>(this.repository.findAll(),false,200,"OK");
    }
    @Transactional(readOnly = true)
    public CustomResponse<Carrito> deteleByUsuario(String correo){
        this.repository.deleteByUsuario(correo);
        return new CustomResponse<>(null,false,200,"OK");
    }
}
