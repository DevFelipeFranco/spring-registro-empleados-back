package com.registro.empleados.springregistroempleadosback.aplicacion.manejador.genero;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Genero;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.PersonaServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
public class ManejadorConsultarGenero {

    private final PersonaServicio personaServicio;

    @Transactional
    public List<Genero> ejecutar() {
        return personaServicio.consultarGenero();
    }
}
