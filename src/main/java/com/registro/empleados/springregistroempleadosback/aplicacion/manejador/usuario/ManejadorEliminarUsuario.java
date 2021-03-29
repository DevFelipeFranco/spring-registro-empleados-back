package com.registro.empleados.springregistroempleadosback.aplicacion.manejador.usuario;

import com.registro.empleados.springregistroempleadosback.dominio.servicio.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class ManejadorEliminarUsuario {

    private final AuthService authService;

    @Transactional
    public String ejecutar(Long idUsuario) {
        return authService.eliminarUsuario(idUsuario);
    }
}
