package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Rol;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.UsuarioRepositorioMySQL;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;

@Service
@AllArgsConstructor
public class AuthService {

    private final UsuarioRepositorioMySQL usuarioRepositorioMySQL;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario registrarUsuario(Usuario usuarioModelo) {
        Usuario usuario = Usuario.builder()
                .conUsuario(usuarioModelo.getUsuario())
                .conClave(passwordEncoder.encode(usuarioModelo.getClave()))
                .conEmail(usuarioModelo.getEmail())
                .conFechaCreacion(new Date())
                .conEstado(false)
                .conRoles(Arrays.asList(new Rol(2L, "Jugador")))
                .build();
        Usuario usuarioCreado = usuarioRepositorioMySQL.registrarUsuario(usuario);

        return usuarioCreado;
    }

}
