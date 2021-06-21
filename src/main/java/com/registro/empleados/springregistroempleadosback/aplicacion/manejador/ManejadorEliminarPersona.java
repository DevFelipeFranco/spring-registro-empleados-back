package com.registro.empleados.springregistroempleadosback.aplicacion.manejador;

import com.registro.empleados.springregistroempleadosback.dominio.servicio.PersonaServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class ManejadorEliminarPersona {

    private final PersonaServicio personaServicio;

    @Transactional
    public void ejecutar(Long idPersona, String motivo) {
        personaServicio.eliminarPersona(idPersona, motivo);
    }
}
