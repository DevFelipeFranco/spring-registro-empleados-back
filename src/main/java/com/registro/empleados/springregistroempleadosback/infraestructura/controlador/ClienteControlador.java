package com.registro.empleados.springregistroempleadosback.infraestructura.controlador;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoCliente;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.ManejadorEliminarClientes;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.cliente.ManejadorConsultarClientes;
import com.registro.empleados.springregistroempleadosback.aplicacion.manejador.cliente.ManejadorCrearClientes;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Cliente;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/clientes")
@AllArgsConstructor
public class ClienteControlador {

    private final ManejadorConsultarClientes manejadorConsultarClientes;
    private final ManejadorCrearClientes manejadorCrearClientes;
    private final ManejadorEliminarClientes manejadorEliminarClientes;

    @GetMapping
    public ResponseEntity<List<Cliente>> consultarClientes(@RequestParam(value = "esActivo", required = false, defaultValue = "true") boolean esActivo) {
        return ResponseEntity.ok(manejadorConsultarClientes.ejecutar(esActivo));
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody ComandoCliente comandoCliente) {
        return ResponseEntity.status(CREATED)
                .body(manejadorCrearClientes.ejecutar(comandoCliente));
    }

    @DeleteMapping("/{idCliente}")
    public void eliminarCliente(@PathVariable("idCliente") Long idCliente) {
        manejadorEliminarClientes.eliminarCliente(idCliente);
    }
}
