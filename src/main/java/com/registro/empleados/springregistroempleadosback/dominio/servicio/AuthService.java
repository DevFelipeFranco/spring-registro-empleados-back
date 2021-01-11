package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Rol;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Token;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.TokenRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.UsuarioRepositorioMySQL;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final UsuarioRepositorioMySQL usuarioRepositorioMySQL;
    private final TokenRepositorioMySQL tokenRepositorioMySQL;
    private final PasswordEncoder passwordEncoder;

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

        generarVerificacionToken(usuario);
        return usuarioCreado;
    }

    private String generarVerificacionToken(Usuario usuario) {
        String token = UUID.randomUUID().toString();
        Token verificacionToken = Token.builder()
                .conToken(token)
                .conUsuario(usuario)
                .conFechaExpiracion(new Date())
                .build();

        tokenRepositorioMySQL.registrarToken(verificacionToken);
        return token;
    }

}
