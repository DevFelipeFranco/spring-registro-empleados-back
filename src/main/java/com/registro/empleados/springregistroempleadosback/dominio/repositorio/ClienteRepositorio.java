package com.registro.empleados.springregistroempleadosback.dominio.repositorio;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Cliente;

import java.util.List;

public interface ClienteRepositorio {

    List<Cliente> consultarClientes(String esActivo);

    Cliente crearCliente(Cliente cliente);

    void eliminarCliente(Long idCliente);
}
