package utez.edu.mx.joyeria.controladores.estatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.joyeria.modelos.estatus.Estatus;
import utez.edu.mx.joyeria.servicios.EstatusService;
import utez.edu.mx.joyeria.extraUtiles.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estatus")
@CrossOrigin(origins = {"*"})
public class EstatusController {
    @Autowired
    EstatusService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Estatus>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Estatus>> insert(@Validated @RequestBody Estatus estatus){
        return new ResponseEntity<>(this.service.insert(estatus),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Estatus>> update(@Validated @RequestBody Estatus estatus){
        return new ResponseEntity<>(this.service.update(estatus),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Estatus>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}
