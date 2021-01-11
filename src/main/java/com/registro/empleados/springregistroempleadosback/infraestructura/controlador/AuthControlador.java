package com.registro.empleados.springregistroempleadosback.infraestructura.controlador;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoUsuario;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorRegistrarUsuario;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
@AllArgsConstructor
public class AuthControlador {

    private final AuthService authService;
    private final ManejadorRegistrarUsuario manejadorRegistrarUsuario;

    @GetMapping(value = "/inicio")
    public String inicio() {
        return "Inicio";
    }

    @PostMapping(value = "signup")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody ComandoUsuario comandoUsuario) {
        return ResponseEntity.ok(manejadorRegistrarUsuario.ejecutar(comandoUsuario));
    }
}
