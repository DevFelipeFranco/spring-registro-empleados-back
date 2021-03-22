package com.registro.empleados.springregistroempleadosback.infraestructura.controlador;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoRefreshToken;
import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoUsuario;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorLogin;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorRegistrarUsuario;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.usuario.ManejadirConsultarUsuario;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.AuthService;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.ProcesarImagenUploadService;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.RefreshTokenServicio;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.Autenticacion;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static com.registro.empleados.springregistroempleadosback.dominio.constants.FileConstant.FORWARD_SLASH;
import static com.registro.empleados.springregistroempleadosback.dominio.constants.FileConstant.TEMP_PROFILE_IMAGE_BASE_URL;

@RestController
@RequestMapping(value = "/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthControlador {

    private final AuthService authService;
    private final ProcesarImagenUploadService procesarImagenUploadService;
    private final ManejadorRegistrarUsuario manejadorRegistrarUsuario;
    private final ManejadirConsultarUsuario manejadirConsultarUsuario;
    private final ManejadorLogin manejadorLogin;
    private final RefreshTokenServicio refreshTokenServicio;

    @GetMapping(value = "/inicio")
    public String inicio() {
        return "Inicio";
    }

    @GetMapping(value = "/allUsuarios")
    public ResponseEntity<List<Usuario>> consultarUsuarios() {
        return ResponseEntity.ok(manejadirConsultarUsuario.ejecutar());
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody ComandoUsuario comandoUsuario) {
        return ResponseEntity.ok(manejadorRegistrarUsuario.ejecutar(comandoUsuario));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Optional<Autenticacion>> login(@RequestBody ComandoUsuario comandoUsuario) {
        return ResponseEntity.ok(manejadorLogin.ejecutar(comandoUsuario));
    }

    @GetMapping(value = "/accountVerification/{token}")
    public ResponseEntity<String> verificacionCuenta(@PathVariable("token") String token) {
        authService.verificarCuenta(token);
        return new ResponseEntity<>("Cuenta activada con exito", HttpStatus.OK);
    }

    @PostMapping(value = "/refresh/token")
    public ResponseEntity<Autenticacion> refreshToken(@Valid @RequestBody ComandoRefreshToken refreshToken) {
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }

    @PostMapping(value = "/imagen/upload")
    public ResponseEntity<Usuario> upload(@RequestParam("imagenPerfil") MultipartFile imagenPerfil, @RequestParam("id") Long id) throws IOException {
        Usuario usuarioActualizado = procesarImagenUploadService.uploadImagenPerfil(imagenPerfil, id);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @GetMapping(path = "/imagen/perfilUsuario/{usuario}/{nombreArchivo}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImagenPerfil(@PathVariable("usuario") String usuario, @PathVariable("nombreArchivo") String nombreArchivo) throws IOException {
        return Files.readAllBytes(Paths.get(procesarImagenUploadService.getPath(usuario) + FORWARD_SLASH + nombreArchivo).normalize());
    }

    @GetMapping(path = "/imagen/perfil/{usuario}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImagenTemporalPerfil(@PathVariable("usuario") String usuario) throws MalformedURLException {
        URL url = new URL(TEMP_PROFILE_IMAGE_BASE_URL + usuario);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (InputStream inputStream = url.openStream()) {
            int bytesRead;
            byte[] chunk = new byte[1024];
            while ((bytesRead = inputStream.read(chunk)) > 0) {
                byteArrayOutputStream.write(chunk, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    @PostMapping(value = "logout")
    public ResponseEntity<String> logout(@Valid @RequestBody ComandoRefreshToken comandoRefreshToken) {
        refreshTokenServicio.eliminarActualizacionToken(comandoRefreshToken.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK)
                .body("Se elimino con exito la actualizacion del token");
    }
}
