package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.PersonaEntidad;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.PersonaRepositorioMySQL;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonaServicio {

    private final PersonaRepositorioMySQL personaRepositorioMySQL;

    public PersonaEntidad registrarPersona(PersonaEntidad personaEntidad) {
        return personaRepositorioMySQL.save(personaEntidad);
    }
}
