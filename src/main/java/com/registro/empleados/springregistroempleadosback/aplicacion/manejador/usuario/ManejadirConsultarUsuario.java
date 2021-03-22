package com.registro.empleados.springregistroempleadosback.aplicacion.manejador.usuario;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
public class ManejadirConsultarUsuario {

    private final AuthService authService;

    @Transactional
    public List<Usuario> ejecutar() {
        return authService.consultarUsuarios();
    }
}
