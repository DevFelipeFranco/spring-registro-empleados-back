package com.registro.empleados.springregistroempleadosback.aplicacion.manejador.usuario;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class ManejadorConsultaUsuarioPorId {

    private final AuthService authService;

    @Transactional
    public Usuario ejecutar(Long idUsuario) {
        return authService.consultarUsuarioPorId(idUsuario);
    }
}
