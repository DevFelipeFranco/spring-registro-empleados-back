package com.registro.empleados.springregistroempleadosback.aplicacion.manejador;

import com.registro.empleados.springregistroempleadosback.dominio.servicio.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class ManejadorEliminarYTransferirUsuario {

    private final AuthService authService;

    @Transactional
    public String ejecutar(Long idUsuario, String usuario) {
        return authService.eliminarYTransferirUsuario(idUsuario, usuario);
    }
}
