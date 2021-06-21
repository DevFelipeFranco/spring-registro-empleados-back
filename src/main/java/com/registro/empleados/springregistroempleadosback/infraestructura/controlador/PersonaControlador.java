package com.registro.empleados.springregistroempleadosback.infraestructura.controlador;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoPersona;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorActualizarPersona;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorConsultarPersonas;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorEliminarPersona;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorRegistroPersona;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.genero.ManejadorConsultarGenero;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.tipoDocumento.ManejadorConsultarTipoDocumento;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.*;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.PersonaServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/api/persona")
@AllArgsConstructor
public class PersonaControlador {

    private final PersonaServicio personaServicio;
    private final ManejadorRegistroPersona manejadorRegistroPersona;
    private final ManejadorConsultarPersonas manejadorConsultarPersonas;
    private final ManejadorActualizarPersona manejadorActualizarPersona;
    private final ManejadorEliminarPersona manejadorEliminarPersona;
    private final ManejadorConsultarTipoDocumento manejadorConsultarTipoDocumento;
    private final ManejadorConsultarGenero manejadorConsultarGenero;

    @GetMapping(value = "")
    public ResponseEntity<List<Persona>> consultarPersonas(@RequestParam(value = "esActivo", required = false, defaultValue = "true") boolean esActivo) {
        return ResponseEntity.ok(manejadorConsultarPersonas.ejecutar(esActivo));
    }

    @GetMapping(value = "/tipoDocumento")
    public ResponseEntity<List<TipoDocumento>> consultarTipoDocumentos() {
        return ResponseEntity.ok(manejadorConsultarTipoDocumento.ejecutar());
    }

    @GetMapping(value = "/genero")
    public ResponseEntity<List<Genero>> consultarGenero() {
        return ResponseEntity.ok(manejadorConsultarGenero.ejecutar());
    }

    @PostMapping(value = "")
    public ResponseEntity<Persona> registrarPersona(@RequestBody ComandoPersona personaEntidad) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(manejadorRegistroPersona.ejecutar(personaEntidad));
    }

    @PutMapping(value = "")
    public ResponseEntity<Persona> actualizarPersona(@RequestBody ComandoPersona comandoPersona) {
        return ResponseEntity.status(OK)
                .body(manejadorActualizarPersona.ejecutar(comandoPersona));
    }

    @DeleteMapping(value = "/{idPersona}/motivo/{motivo}")
    public void eliminarPersona(@PathVariable("idPersona") Long idPersona, @PathVariable("motivo") String motivo) {
        manejadorEliminarPersona.ejecutar(idPersona, motivo);
    }

    @GetMapping(value = "/consultarPersonasContratadasPorMes")
    public ResponseEntity<List<CantidadEmpleadosContratadosMes>> consultarPersonasContratadasPorMes() {
        return ResponseEntity.ok(personaServicio.consultarPersonasContratadasPorMes());
    }
}
