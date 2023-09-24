package utez.edu.mx.joyeria.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import utez.edu.mx.joyeria.modelos.usuarios.Usuarios;
import utez.edu.mx.joyeria.modelos.usuarios.UsuariosRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuariosRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario=repository.getOneByCorreo(username)
                .orElseThrow(()->new UsernameNotFoundException("EL usuario: "+username+" no existe"));
        return new UserDetailsImpl(usuario);
    }

}
