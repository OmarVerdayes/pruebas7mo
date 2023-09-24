package utez.edu.mx.joyeria.controladores.productos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utez.edu.mx.joyeria.modelos.productos.Productos;
import utez.edu.mx.joyeria.servicios.ProductosService;
import utez.edu.mx.joyeria.extraUtiles.CustomResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = {"*"})
public class ProductosController {

    @Autowired
    ProductosService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Productos>>> getByAllActivos(){
        return new ResponseEntity<>(this.service.getActivos(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Productos>> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.service.getOne(id),HttpStatus.OK);
    }
    @GetMapping("/todos/")
    public ResponseEntity<CustomResponse<List<Productos>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Productos>> insert(@Validated @ModelAttribute Productos productos, MultipartFile fotoArchivo) throws IOException {
        return new ResponseEntity<>(this.service.insert(productos,fotoArchivo),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Productos>> update(@Validated  @ModelAttribute Productos productos, MultipartFile fotoArchivo) throws IOException {
        return new ResponseEntity<>(this.service.update(productos,fotoArchivo),HttpStatus.CREATED);
    }
    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Productos>> updateEstatus(@Validated  @RequestBody Map<String,Long> requets) {
        Long id=requets.get("id");
        Long nuevoEstatus=requets.get("id");
        return new ResponseEntity<>(this.service.patchEstatus(id,nuevoEstatus),HttpStatus.CREATED);
    }
}
