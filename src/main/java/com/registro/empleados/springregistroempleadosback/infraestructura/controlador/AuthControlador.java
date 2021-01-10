package com.registro.empleados.springregistroempleadosback.infraestructura.controlador;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.UsuarioModelo;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/auth")
@AllArgsConstructor
public class AuthControlador {

    private final AuthService authService;

    @GetMapping(value = "/inicio")
    public String inicio() {
        return "Inicio";
    }

    @GetMapping(value = "/consultar")
    public ResponseEntity<?> consultar() {
        return ResponseEntity.ok(authService.listaUsuarios());
    }

    @GetMapping(value = "/prueba")
    public ResponseEntity<?> prueba() {
        return ResponseEntity.ok(authService.prueba());
    }

    @PostMapping(value = "signup")
    public ResponseEntity<UsuarioModelo> registrarUsuario(@RequestBody UsuarioModelo usuarioModelo) {
        return ResponseEntity.ok(authService.registrarUsuario(usuarioModelo));
    }
}
