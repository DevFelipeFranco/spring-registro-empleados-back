package com.registro.empleados.springregistroempleadosback.aplicacion.manejador;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Persona;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.PersonaServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@AllArgsConstructor
public class ManejadorConsultarPersonas {

    private final PersonaServicio personaServicio;

    @Transactional
    public List<Persona> ejecutar(Boolean esActivo) {
        return personaServicio.consultarPersonas(esActivo);
    }

}
