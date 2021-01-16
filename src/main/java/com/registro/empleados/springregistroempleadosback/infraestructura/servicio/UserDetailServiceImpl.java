package com.registro.empleados.springregistroempleadosback.infraestructura.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.UsuarioPrincipal;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.UsuarioRepositorioMySQL;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UsuarioRepositorioMySQL usuarioRepositorioMySQL;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usuarioABuscar) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepositorioMySQL.buscarUsuario(usuarioABuscar);
        Usuario usuario = usuarioOpt.orElseThrow(() -> new UsernameNotFoundException(String.format("No se encontrol el usuario: %s", usuarioABuscar)));

        return new UsuarioPrincipal(usuario);
    }
}
