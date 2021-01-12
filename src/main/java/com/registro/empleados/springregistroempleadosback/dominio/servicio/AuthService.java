package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Rol;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Token;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.NotificacionEmail;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.TokenRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.UsuarioRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.servicio.MailService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final UsuarioRepositorioMySQL usuarioRepositorioMySQL;
    private final TokenRepositorioMySQL tokenRepositorioMySQL;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

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

        String token = generarVerificacionToken(usuario);
        enviarEmail(token, usuarioModelo.getEmail());
        return usuarioCreado;
    }

    private void enviarEmail(String token, String email) {
        mailService.sendMail(new NotificacionEmail("Por favor activa tu cuenta",
                email, "Gracias por registrarte, " +
                "por favor da click en la URL a continuacion para activar tu cuenta : " +
                "http://localhost:8081/api/auth/accountVerification/" + token));
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
