package com.registro.empleados.springregistroempleadosback.aplicacion.manejador.cliente;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Cliente;
import com.registro.empleados.springregistroempleadosback.dominio.servicio.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
public class ManejadorConsultarClientes {

    private final ClienteService clienteService;

    @Transactional
    public List<Cliente> ejecutar(Boolean esActivo) {
        return clienteService.consultarClientes(esActivo);
    }
}
