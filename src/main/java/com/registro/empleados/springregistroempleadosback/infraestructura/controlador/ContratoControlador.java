package com.registro.empleados.springregistroempleadosback.infraestructura.controlador;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoContrato;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.contrato.ManejadorConsultarContratos;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.contrato.ManejadorRegistrarContrato;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Contrato;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/contrato")
@AllArgsConstructor
public class ContratoControlador {

    private final ManejadorConsultarContratos manejadorConsultarContratos;
    private final ManejadorRegistrarContrato manejadorRegistrarContrato;

    @GetMapping(value = "")
    public ResponseEntity<List<Contrato>> consultarContratos() {
        return ResponseEntity.ok(manejadorConsultarContratos.ejecutar());
    }

    @PostMapping(value = "")
    public ResponseEntity<Contrato> crearInformacionContrato(@RequestBody ComandoContrato comandoContrato) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(manejadorRegistrarContrato.ejecutar(comandoContrato));
    }
}
