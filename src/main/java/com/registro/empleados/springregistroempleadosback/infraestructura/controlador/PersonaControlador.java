package com.registro.empleados.springregistroempleadosback.infraestructura.controlador;

import com.registro.empleados.springregistroempleadosback.dominio.servicio.PersonaServicio;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.PersonaEntidad;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/persona")
@AllArgsConstructor
public class PersonaControlador {

    private final PersonaServicio personaServicio;

    @PostMapping(value = "/registro")
    public ResponseEntity<PersonaEntidad> registrarPersona(@RequestBody PersonaEntidad personaEntidad) {
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(personaServicio.registrarPersona(personaEntidad));
    }
}
