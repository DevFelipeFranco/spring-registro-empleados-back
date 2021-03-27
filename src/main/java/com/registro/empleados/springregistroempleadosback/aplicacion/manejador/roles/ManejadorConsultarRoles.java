package com.registro.empleados.springregistroempleadosback.aplicacion.manejador.roles;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Rol;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ManejadorConsultarRoles {

    private final AuthService authService;

    public List<Rol> ejecutar() {
        return authService.consultarRoles();
    }
}
