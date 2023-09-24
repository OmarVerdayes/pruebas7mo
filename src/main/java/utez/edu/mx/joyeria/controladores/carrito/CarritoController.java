package utez.edu.mx.joyeria.controladores.carrito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.joyeria.modelos.carrito.Carrito;
import utez.edu.mx.joyeria.modelos.productos.Productos;
import utez.edu.mx.joyeria.servicios.CarritoService;
import utez.edu.mx.joyeria.extraUtiles.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = {"*"})
public class CarritoController {

    @Autowired
    CarritoService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Productos>>> get(@Validated @RequestBody Map<String,String> requets){
        String correo=requets.get("correo");
        return new ResponseEntity<>(this.service.getByUsuario(correo), HttpStatus.OK);
    }
    
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Carrito>> insert(@Validated @RequestBody Carrito carrito){

        return new ResponseEntity<>(this.service.insert(carrito), HttpStatus.OK);
    }

}
