package utez.edu.mx.joyeria.controladores.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.joyeria.modelos.ventas.Ventas;
import utez.edu.mx.joyeria.servicios.VentasService;
import utez.edu.mx.joyeria.extraUtiles.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = {"*"})
public class VentasController {
    @Autowired
    VentasService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Ventas>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Ventas>> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.service.getOne(id),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Ventas>> insert(@Validated @RequestBody VentasInfo venta)  {
        return new ResponseEntity<>(this.service.insert(venta),HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<Integer>> update(@Validated  @RequestBody Map<String,Long> requets )  {
        Long id=requets.get("id");
        Long nuevoEstatus=requets.get("nuevoEstatus");
        return new ResponseEntity<>(this.service.updateEstatus(id,nuevoEstatus),HttpStatus.CREATED);
    }

    @GetMapping("/historial")
    public ResponseEntity<CustomResponse<List<Ventas>>> history(@Validated  @RequestBody Map<String,String> requets){
        String correo=requets.get("correo");
        return new ResponseEntity<>(this.service.getHistory(correo),HttpStatus.CREATED);
    }
    @GetMapping("/proceso")
    public ResponseEntity<CustomResponse<List<Ventas>>> process(@Validated  @RequestBody Map<String,String> requets){
        String correo=requets.get("correo");
        return new ResponseEntity<>(this.service.getInProcess(correo),HttpStatus.CREATED);
    }
}
