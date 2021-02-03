package com.registro.empleados.springregistroempleadosback.infraestructura.controlador;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoRefreshToken;
import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoUsuario;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorLogin;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorRegistrarUsuario;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.AuthService;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.RefreshTokenServicio;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.Autenticacion;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/auth")
@AllArgsConstructor
public class AuthControlador {

    private final AuthService authService;
    private final ManejadorRegistrarUsuario manejadorRegistrarUsuario;
    private final ManejadorLogin manejadorLogin;
    private final RefreshTokenServicio refreshTokenServicio;

    @GetMapping(value = "/inicio")
    public String inicio() {
        return "Inicio";
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody ComandoUsuario comandoUsuario) {
        return ResponseEntity.ok(manejadorRegistrarUsuario.ejecutar(comandoUsuario));
    }

    @GetMapping(value = "/accountVerification/{token}")
    public ResponseEntity<String> verificacionCuenta(@PathVariable("token") String token) {
        authService.verificarCuenta(token);
        return new ResponseEntity<>("Cuenta activada con exito", HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Optional<Autenticacion>> login(@RequestBody ComandoUsuario comandoUsuario) {
        return ResponseEntity.ok(manejadorLogin.ejecutar(comandoUsuario));
    }

    @PostMapping(value = "/refresh/token")
    public ResponseEntity<Autenticacion> refreshToken(@Valid @RequestBody ComandoRefreshToken refreshToken) {
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }

    @PostMapping(value = "logout")
    public ResponseEntity<String> logout(@Valid @RequestBody ComandoRefreshToken comandoRefreshToken) {
        refreshTokenServicio.eliminarActualizacionToken(comandoRefreshToken.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK)
                .body("Se elimino con exito la actualizacion del token");
    }
}
