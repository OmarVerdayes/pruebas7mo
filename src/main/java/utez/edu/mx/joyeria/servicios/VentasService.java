package utez.edu.mx.joyeria.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import utez.edu.mx.joyeria.controladores.ventas.VentasInfo;
import utez.edu.mx.joyeria.modelos.carrito.CarritoRepository;
import utez.edu.mx.joyeria.modelos.estatus.Estatus;
import utez.edu.mx.joyeria.modelos.productos.Productos;
import utez.edu.mx.joyeria.modelos.productos.ProductosRepository;
import utez.edu.mx.joyeria.modelos.usuarios.Usuarios;
import utez.edu.mx.joyeria.modelos.ventas.Ventas;
import utez.edu.mx.joyeria.modelos.ventas.VentasRepository;
import utez.edu.mx.joyeria.extraUtiles.CustomResponse;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentasService {
    @Autowired
    VentasRepository repository;
    @Autowired
    ProductosRepository  productosRepository;
    @Autowired
    CarritoRepository carritoRepository;


    @Transactional(readOnly=true)
    public CustomResponse<List<Ventas>> getAll(){
        return new CustomResponse<>(this.repository.findAll(),false,200,"OK");
    }
    @Transactional(readOnly = true)
    public CustomResponse<Ventas> getOne(Long id){
        return new CustomResponse<>(this.repository.findById(id).get(),false,200,"OK");
    }
    @Transactional(readOnly=true)
    public CustomResponse<List<Ventas>> getHistory(String correo){
        return new CustomResponse<>(this.repository.findHistoryByCorreo(correo),false,200,"OK");
    }
    @Transactional(readOnly=true)
    public CustomResponse<List<Ventas>> getInProcess(String correo){
        return new CustomResponse<>(this.repository.findInProcess(correo),false,200,"OK");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Ventas> insert(@Validated VentasInfo venta){

        Productos pro=this.productosRepository.findById(venta.getId_producto()).get();

        if(venta.getCantidad_productos()<= 0 || venta.getCantidad_productos()>pro.getStock()){
            return new CustomResponse<>(null, false,400,"La cantidad de piezas que desea comprar excede el stock");
        } else if (pro.getEstatus()==0) {
            return new CustomResponse<>(null, false,400,"El producto: "+pro.getNombre()+" no se encuantra disponible");
        }

        pro.setStock(pro.getStock()-venta.getCantidad_productos());

        if(pro.getStock()==0){
            pro.setEstatus(0L);
        }

        this.productosRepository.saveAndFlush(pro);

        Estatus est=new Estatus(1L,null);
        Usuarios usu=new Usuarios();
        usu.setCorreo(venta.getCorreo());


        Ventas registrarVenta = new Ventas(null,String.valueOf(LocalDateTime.now()),null,pro.getPrecio()*venta.getCantidad_productos(), venta.getCantidad_productos(),est,usu,pro );

        return new CustomResponse<>(this.repository.saveAndFlush(registrarVenta), false,200,"venta registrada!");
    }

    @Transactional
    public CustomResponse<Integer> updateEstatus(Long id, Long nuevoEstatus) {
        if (!repository.existsById(id)) {
            return new CustomResponse<>(null, true, 400, "La venta no existe");
        }

        return new CustomResponse<>(this.repository.updateEstatusById(id,nuevoEstatus), false,200,"Estatus de venta actualizado");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Ventas> buyCar(@Validated List<Ventas> listaVentas){

        for(int i=0; i<listaVentas.size();i++){
            Ventas ventas=listaVentas.get(i);
            Productos pro=this.productosRepository.findById(ventas.getProductos().getId()).get();
            if(ventas.getPiezas()<= 0 || ventas.getPiezas()>pro.getStock()){
                return new CustomResponse<>(null, false,400,"La cantidad de piezas del producto: "+pro.getNombre()+" que desea comprar excede el stock ");
            }else if (pro.getEstatus()==0) {
                return new CustomResponse<>(null, false,400,"El producto "+pro.getNombre()+" no se encuantra disponible");
            }
        }

       // List<Ventas> vendidos = null;

        for (int i=0;i<listaVentas.size();i++){
            Ventas ventas=listaVentas.get(i);
            Productos pro=this.productosRepository.findById(ventas.getProductos().getId()).get();
            pro.setStock(pro.getStock()-ventas.getPiezas());

            if(pro.getStock()==0){
                pro.setEstatus(0L);
            }

            this.productosRepository.saveAndFlush(pro);
            //endidos.add(this.repository.saveAndFlush(ventas));
            this.repository.saveAndFlush(ventas);
        }

        this.carritoRepository.deleteByUsuario(listaVentas.get(0).getUsuarios().getCorreo());
        return new CustomResponse<>(null, false,200,"Las ventas han sido registradas");

    }


}
