package utez.edu.mx.joyeria.controladores.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.joyeria.modelos.usuarios.Usuarios;
import utez.edu.mx.joyeria.servicios.UsuariosService;
import utez.edu.mx.joyeria.extraUtiles.CustomResponse;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = {"*"})
public class UsuariosController {
    @Autowired
    UsuariosService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Usuarios>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Usuarios>> getById(@PathVariable("id") String correo){
        return new ResponseEntity<>(this.service.getOne(correo),HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<CustomResponse<Usuarios>> insert(@Validated @RequestBody Usuarios usuarios)  {
        return new ResponseEntity<>(this.service.insert(usuarios),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Usuarios>> update(@Validated  @ModelAttribute Usuarios usuarios)  {
        return new ResponseEntity<>(this.service.update(usuarios),HttpStatus.CREATED);
    }
    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Usuarios>> updateEstatus(@Validated  @RequestBody String correo, Long nuevoEstatus ) {
        return new ResponseEntity<>(this.service.patchEstatus(correo,nuevoEstatus),HttpStatus.CREATED);
    }
}
