package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoRefreshToken;
import com.registro.empleados.springregistroempleadosback.dominio.excepciones.NoExisteTokenException;
import com.registro.empleados.springregistroempleadosback.dominio.excepciones.UsuarioExisteException;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Rol;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Token;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.UsuarioPrincipal;
import com.registro.empleados.springregistroempleadosback.dominio.transformadores.UsuarioTransformer;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.Autenticacion;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.NotificacionEmail;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.RolRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.TokenRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.UsuarioRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.security.JwtProvider;
import com.registro.empleados.springregistroempleadosback.infraestructura.servicio.MailService;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.UsuarioTransformador;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private final RefreshTokenServicio refreshTokenServicio;
    private final ProcesarImagenUploadService procesarImagenUploadService;
    private final RolRepositorioMySQL rolRepositorioMySQL;

    public Usuario registrarUsuario(Usuario usuarioModelo) {
        existeUsuario(usuarioModelo.getUsuario());
        String imagenPerfilTemporal = procesarImagenUploadService.getImagenPerfilTemporal(usuarioModelo.getUsuario());
        Usuario usuario = Usuario.builder()
                .conNombres(usuarioModelo.getNombres())
                .conApellidos(usuarioModelo.getApellidos())
                .conUsuario(usuarioModelo.getUsuario())
                .conClave(passwordEncoder.encode(usuarioModelo.getClave()))
                .conEmail(usuarioModelo.getEmail())
                .conFechaCreacion(LocalDateTime.now())
                .conEstado(false)
                .conSnNoBloqueado(true)
                .conImagenPerfilUrl(imagenPerfilTemporal)
                .conRoles(Collections.singletonList(new Rol(1L, "ROLE_USER", new ArrayList<>())))
                .build();

        Token token = generarVerificacionToken(usuario);
        enviarEmail(token.getToken(), usuarioModelo.getEmail());
        return token.getUsuario();
    }

    private void existeUsuario(String nombreUsaurio) {
        if (usuarioRepositorioMySQL.buscarUsuario(nombreUsaurio).isPresent()) {
            throw new UsuarioExisteException("Ya existe un usuario registrado");
        }
    }

    private void enviarEmail(String token, String email) {
        mailService.sendMail(new NotificacionEmail("Por favor activa tu cuenta",
                email, "Gracias por registrarte, " +
                "por favor da click en la URL a continuacion para activar tu cuenta : " +
                "http://localhost:9003/api/auth/accountVerification/" + token));
    }

    private Token generarVerificacionToken(Usuario usuario) {
        String token = UUID.randomUUID().toString();
        Token verificacionToken = Token.builder()
                .conToken(token)
                .conUsuario(usuario)
                .conFechaExpiracion(LocalDateTime.now())
                .build();

        return tokenRepositorioMySQL.registrarToken(verificacionToken);
    }

    @Transactional
    public void verificarCuenta(String token) {
        Optional<Token> verificacionToken = tokenRepositorioMySQL.buscarToken(token);
        verificacionToken.orElseThrow(() -> new NoExisteTokenException("Token Invalido"));
        buscarUsaurioYHabilitar(verificacionToken.get());
    }

    private void buscarUsaurioYHabilitar(Token token) {
        token.getUsuario().setEstado(true);
        usuarioRepositorioMySQL.activarUsuario(UsuarioTransformador.transformBooleanToString(token.getUsuario().getEstado()), token.getIdToken());
    }

    public Optional<Autenticacion> login(Usuario usuario) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsuario(),
                usuario.getClave()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generarToken(authenticate);
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authenticate.getPrincipal();

        return UsuarioTransformer.usuarioToAutenticacion(token, Optional.of(usuarioPrincipal.getUsuario()), jwtProvider.getExpiracionJwt(), refreshTokenServicio.generarRefreshToken().getToken());

    }

    public Autenticacion refreshToken(ComandoRefreshToken refreshToken) {
        refreshTokenServicio.validateActualizacionToken(refreshToken.getRefreshToken());
        String token = jwtProvider.generarTokenConUsuario(refreshToken.getUsuario());
        Optional<Usuario> usuario = usuarioRepositorioMySQL.buscarUsuario(refreshToken.getUsuario());

        return Autenticacion.builder()
                .tokenAutenticacion(token)
                .refreshToken(refreshToken.getRefreshToken())
                .expiresAt(LocalDateTime.now().plusSeconds(jwtProvider.getExpiracionJwt()))
                .usuario(UsuarioTransformer.usuariOptSinClave(usuario))
                .build();

    }

    public List<Usuario> consultarUsuarios() {
        return UsuarioTransformer.usuariosSinClave(usuarioRepositorioMySQL.consultarUsuarios());
    }

    public Usuario consultarUsuarioPorId(Long idUsuario) {
        return UsuarioTransformer.usuarioSinClave(usuarioRepositorioMySQL.consultarUsuarioPorId(idUsuario));
    }

    public Usuario actualizarUsuario(Usuario usuarioActualizado) {
        Usuario usuario = usuarioRepositorioMySQL.consultarUsuarioPorId(usuarioActualizado.getIdUsuario());
        usuario.setUsuario(usuarioActualizado.getUsuario());
        usuario.setNombres(usuarioActualizado.getNombres());
        usuario.setApellidos(usuarioActualizado.getApellidos());
        usuario.setEmail(usuarioActualizado.getEmail());
        usuario.setEstado(usuarioActualizado.getEstado());
        usuario.setSnNoBloqueado(usuarioActualizado.getSnNoBloqueado());
        usuario.setCargo(usuarioActualizado.getCargo());
        usuario.setCelular(usuarioActualizado.getCelular());
        usuario.setRoles(usuarioActualizado.getRoles());
        usuarioRepositorioMySQL.actualizarInformacionUsuario(usuario);
        return UsuarioTransformer.usuarioSinClave(usuario);
    }

    public List<Rol> consultarRoles() {
        return rolRepositorioMySQL.consultarRoles();
    }
}
