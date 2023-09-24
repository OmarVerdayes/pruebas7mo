package utez.edu.mx.joyeria.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import utez.edu.mx.joyeria.modelos.usuarios.Usuarios;
import utez.edu.mx.joyeria.modelos.usuarios.UsuariosRepository;
import utez.edu.mx.joyeria.extraUtiles.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuariosService {

    @Autowired
    UsuariosRepository repository;
    @Transactional(readOnly=true)
    public CustomResponse<List<Usuarios>> getAll(){
        return new CustomResponse<>(this.repository.findAll(),false,200,"OK");
    }
    @Transactional(readOnly = true)
    public CustomResponse<Usuarios> getOne(String correo){
        return new CustomResponse<>(this.repository.getOneByCorreo(correo).get(),false,200,"OK");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Usuarios> insert(@Validated Usuarios usuarios){
        if(this.repository.existsByCorreo(usuarios.getCorreo()))
            return new CustomResponse<>(null,true,400,"Ya existe un usuario con ese correo");

        String contrasenaEncrip=new BCryptPasswordEncoder().encode(usuarios.getContrasena());
        usuarios.setContrasena(contrasenaEncrip);

        return new CustomResponse<>(this.repository.saveAndFlush(usuarios), false,200,"Usuarios registrado!");
    }
    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Usuarios> update(Usuarios usuarios){
        if(!this.repository.existsByCorreo(usuarios.getCorreo()))
            return new CustomResponse<>(null,true,400,"El Usuarios no existe");

        String contrasenaEncrip=new BCryptPasswordEncoder().encode(usuarios.getContrasena());
        usuarios.setContrasena(contrasenaEncrip);

        return new CustomResponse<>(this.repository.saveAndFlush(usuarios),false,200,"Usuarios actualizado");
    }
    @Transactional
    public CustomResponse<Usuarios> patchEstatus(String correo, Long nuevoEstatus) {
        if (!repository.existsByCorreo(correo)) {
            return new CustomResponse<>(null, true, 400, "El usuario no existe");
        }
        return new CustomResponse<>(this.repository.updateEstatusById(nuevoEstatus,correo), false, 200, "Estatus actualizado");
    }

}
