package com.registro.empleados.springregistroempleadosback.infraestructura.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.UsuarioPrincipal;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.UsuarioRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.security.LoginAttemptService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    private final UsuarioRepositorioMySQL usuarioRepositorioMySQL;
    private final LoginAttemptService loginAttemptService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usuarioABuscar) throws UsernameNotFoundException {
//        Optional<Usuario> usuarioOpt = usuarioRepositorioMySQL.buscarUsuario(usuarioABuscar);
//        Usuario usuario = usuarioOpt.orElseThrow(() -> new UsernameNotFoundException(String.format("No se encontrol el usuario: %s", usuarioABuscar)));


        return usuarioRepositorioMySQL.buscarUsuario(usuarioABuscar)
                .map(usuarioEncontrado -> {
                    validatLoginAttempt(usuarioEncontrado);
                    UsuarioPrincipal usuarioPrincipal = new UsuarioPrincipal(actualizarFechaIngresoAlSistema(usuarioEncontrado));
                    log.info("Retorna el usuario encontrado por el usuario: " + usuarioABuscar);
                    return usuarioPrincipal;
                })
                .orElseThrow(() -> new UsernameNotFoundException(String.format("No se encontrol el usuario: %s", usuarioABuscar)));

//        return new UsuarioPrincipal(usuario);
    }

    private Usuario actualizarFechaIngresoAlSistema(Usuario usuarioEncontrado) {
        usuarioEncontrado.setFechaUltimoIngresoVisualizacion(usuarioEncontrado.getFechaUltimoIngreso());
        usuarioEncontrado.setFechaUltimoIngreso(LocalDateTime.now());
        usuarioRepositorioMySQL.actualizarFechaIngreso(usuarioEncontrado);

        return usuarioEncontrado;
    }

    private void validatLoginAttempt(Usuario usuario) {
        if(usuario.getSnNoBloqueado()) {
            if (loginAttemptService.hasExceededMaxAttempts(usuario.getUsuario())) {
                usuario.setSnNoBloqueado(false);
            } else {
                usuario.setSnNoBloqueado(true);
            }
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(usuario.getUsuario());
        }
    }
}
