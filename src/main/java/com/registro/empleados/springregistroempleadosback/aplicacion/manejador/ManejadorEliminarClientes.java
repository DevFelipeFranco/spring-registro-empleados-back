package com.registro.empleados.springregistroempleadosback.aplicacion.manejador;

import com.registro.empleados.springregistroempleadosback.dominio.servicio.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class ManejadorEliminarClientes {

    private final ClienteService clienteService;

    @Transactional
    public void eliminarCliente(Long idCliente) {
        clienteService.eliminarCliente(idCliente);
    }
}
