package utez.edu.mx.joyeria.controladores.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.joyeria.modelos.categoria.Categoria;
import utez.edu.mx.joyeria.servicios.CategoriaService;
import utez.edu.mx.joyeria.extraUtiles.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = {"*"})
public class CategoriaController {
    @Autowired
    CategoriaService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Categoria>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Categoria>> insert(@Validated @RequestBody Categoria categoria){
        return new ResponseEntity<>(this.service.insert(categoria),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Categoria>> update(@Validated @RequestBody Categoria categoria){
        return new ResponseEntity<>(this.service.update(categoria),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Categoria>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.CREATED);
    }
}
