package com.registro.empleados.springregistroempleadosback.infraestructura.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.excepciones.UsuarioNoExisteException;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.UsuarioRepositorioMySQL;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
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
        return new User(usuario.getUsuario(), usuario.getClave(), usuario.getEstado(),
                true, true, true, getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String roles) {
        return Collections.singletonList(new SimpleGrantedAuthority(roles));
    }
}
