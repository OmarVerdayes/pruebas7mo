package utez.edu.mx.joyeria.controladores.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.joyeria.modelos.roles.Roles;
import utez.edu.mx.joyeria.servicios.RolesService;
import utez.edu.mx.joyeria.extraUtiles.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = {"*"})
public class RolesController {
    @Autowired
    RolesService service;
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Roles>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Roles>> insert(@Validated @RequestBody Roles roles){
        return new ResponseEntity<>(this.service.insert(roles),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Roles>> update(@Validated @RequestBody Roles roles){
        return new ResponseEntity<>(this.service.update(roles),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Roles>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}
