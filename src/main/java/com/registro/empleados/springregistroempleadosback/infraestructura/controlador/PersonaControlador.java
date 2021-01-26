package com.registro.empleados.springregistroempleadosback.infraestructura.controlador;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoPersona;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorActualizarPersona;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorConsultarPersonas;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorEliminarPersona;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorRegistroPersona;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Persona;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/persona")
@AllArgsConstructor
public class PersonaControlador {

    private final ManejadorRegistroPersona manejadorRegistroPersona;
    private final ManejadorConsultarPersonas manejadorConsultarPersonas;
    private final ManejadorActualizarPersona manejadorActualizarPersona;
    private final ManejadorEliminarPersona manejadorEliminarPersona;

    @GetMapping(value = "")
    public ResponseEntity<List<Persona>> consultarPersonas() {
        return ResponseEntity.ok(manejadorConsultarPersonas.ejecutar());
    }

    @PostMapping(value = "/registro")
    public ResponseEntity<Persona> registrarPersona(@RequestBody ComandoPersona personaEntidad) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(manejadorRegistroPersona.ejecutar(personaEntidad));
    }

    @PutMapping(value = "")
    public ResponseEntity<Persona> actualizarPersona(@RequestBody ComandoPersona comandoPersona) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(manejadorActualizarPersona.ejecutar(comandoPersona));
    }

    @DeleteMapping(value = "/{idPersona}")
    public void eliminarPersona(@PathVariable("idPersona") Long idPersona) {
        manejadorEliminarPersona.ejecutar(idPersona);
    }
}
