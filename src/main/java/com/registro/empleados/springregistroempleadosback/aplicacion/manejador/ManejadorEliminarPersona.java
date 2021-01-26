package com.registro.empleados.springregistroempleadosback.aplicacion.manejador;

import com.registro.empleados.springregistroempleadosback.dominio.servicio.PersonaServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ManejadorEliminarPersona {

    private final PersonaServicio personaServicio;


    public void ejecutar(Long idPersona) {
        personaServicio.eliminarPersona(idPersona);
    }
}
