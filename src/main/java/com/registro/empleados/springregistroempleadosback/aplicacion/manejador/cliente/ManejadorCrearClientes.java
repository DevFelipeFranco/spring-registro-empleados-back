package com.registro.empleados.springregistroempleadosback.aplicacion.manejador.cliente;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoCliente;
import com.registro.empleados.springregistroempleadosback.aplicacion.fabrica.FabricaCliente;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Cliente;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ManejadorCrearClientes {

    private final ClienteService clienteService;

    public Cliente ejecutar(ComandoCliente comandoCliente) {
        Cliente cliente = FabricaCliente.comandoClienteToModel(comandoCliente);
        return clienteService.crearCliente(cliente);
    }
}
