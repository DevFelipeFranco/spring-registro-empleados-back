package com.registro.empleados.springregistroempleadosback.infraestructura.controlador;

import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.contrato.ManejadorConsultarContratos;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Contrato;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/contrato")
@AllArgsConstructor
public class ContratoControlador {

    private final ManejadorConsultarContratos manejadorConsultarContratos;

    @GetMapping(value = "")
    public ResponseEntity<List<Contrato>> consultarContratos() {
        return ResponseEntity.ok(manejadorConsultarContratos.ejecutar());
    }
}
