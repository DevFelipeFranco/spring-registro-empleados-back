package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.excepciones.NoExisteTokenException;
import com.registro.empleados.springregistroempleadosback.dominio.excepciones.UsuarioNoExisteException;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Rol;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Token;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.transformadores.UsuarioTransformer;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.Autenticacion;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.NotificacionEmail;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.TokenRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.UsuarioRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.security.JwtProvider;
import com.registro.empleados.springregistroempleadosback.infraestructura.servicio.MailService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AuthService {

    private final UsuarioRepositorioMySQL usuarioRepositorioMySQL;
    private final TokenRepositorioMySQL tokenRepositorioMySQL;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public Usuario registrarUsuario(Usuario usuarioModelo) {
        Usuario usuario = Usuario.builder()
                .conUsuario(usuarioModelo.getUsuario())
                .conClave(passwordEncoder.encode(usuarioModelo.getClave()))
                .conEmail(usuarioModelo.getEmail())
                .conFechaCreacion(new Date())
                .conEstado(false)
                .conRoles(Collections.singletonList(new Rol(2L, "Jugador")))
                .build();

        Token token = generarVerificacionToken(usuario);
        enviarEmail(token.getToken(), usuarioModelo.getEmail());
        return token.getUsuario();
    }

    private void enviarEmail(String token, String email) {
        mailService.sendMail(new NotificacionEmail("Por favor activa tu cuenta",
                email, "Gracias por registrarte, " +
                "por favor da click en la URL a continuacion para activar tu cuenta : " +
                "http://localhost:8081/api/auth/accountVerification/" + token));
    }

    private Token generarVerificacionToken(Usuario usuario) {
        String token = UUID.randomUUID().toString();
        Token verificacionToken = Token.builder()
                .conToken(token)
                .conUsuario(usuario)
                .conFechaExpiracion(new Date())
                .build();

        return tokenRepositorioMySQL.registrarToken(verificacionToken);
    }

    public void verificarCuenta(String token) {
        Optional<Token> verificacionToken = tokenRepositorioMySQL.buscarToken(token);
        verificacionToken.orElseThrow(() -> new NoExisteTokenException("Token Invalido"));
        buscarUsaurioYHabilitar(verificacionToken.get());
    }

    private void buscarUsaurioYHabilitar(Token token) {
        String usuario = token.getUsuario().getUsuario();
        Usuario usuarioEncontrado = usuarioRepositorioMySQL.buscarUsuario(usuario).orElseThrow(() -> new UsuarioNoExisteException("No se encontro el usuario con el nombre: " + usuario));
        usuarioEncontrado.setEstado(true);
        usuarioRepositorioMySQL.registrarUsuario(usuarioEncontrado);
    }

    public Optional<Autenticacion> login(Usuario usuario) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsuario(),
                usuario.getClave()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generarToken(authenticate);

        return UsuarioTransformer.usuarioToAutenticacion(token, usuarioRepositorioMySQL.buscarUsuario(usuario.getUsuario()));

    }
}
