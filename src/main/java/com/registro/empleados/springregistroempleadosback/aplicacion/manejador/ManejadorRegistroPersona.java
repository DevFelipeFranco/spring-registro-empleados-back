package com.registro.empleados.springregistroempleadosback.aplicacion.manejador;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoPersona;
import com.registro.empleados.springregistroempleadosback.aplicacion.fabrica.FabricaPersona;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Persona;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.PersonaServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@AllArgsConstructor
public class ManejadorRegistroPersona {

    private final PersonaServicio personaServicio;

    @Transactional
    public Persona ejecutar(ComandoPersona comandoPersona) {
        Persona persona = FabricaPersona.comandoPersonaToPersona(comandoPersona);
        return personaServicio.registrarPersona(persona);
    }
}
