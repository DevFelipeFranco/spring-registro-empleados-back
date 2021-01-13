package com.registro.empleados.springregistroempleadosback.infraestructura.controlador;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoUsuario;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorLogin;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorRegistrarUsuario;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.AuthService;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.Autenticacion;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
@AllArgsConstructor
public class AuthControlador {

    private final AuthService authService;
    private final ManejadorRegistrarUsuario manejadorRegistrarUsuario;
    private final ManejadorLogin manejadorLogin;

    @GetMapping(value = "/inicio")
    public String inicio() {
        return "Inicio";
    }

    @PostMapping(value = "signup")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody ComandoUsuario comandoUsuario) {
        return ResponseEntity.ok(manejadorRegistrarUsuario.ejecutar(comandoUsuario));
    }

    @GetMapping(value = "/accountVerification/{token}")
    public ResponseEntity<String> verificacionCuenta(@PathVariable("token") String token) {
        authService.verificarCuenta(token);
        return new ResponseEntity<>("Cuenta activada con exito", HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Autenticacion> login(@RequestBody ComandoUsuario comandoUsuario) {
        return ResponseEntity.ok(manejadorLogin.ejecutar(comandoUsuario));
    }
}
