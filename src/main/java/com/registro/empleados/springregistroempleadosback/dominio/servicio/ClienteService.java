package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.excepciones.ClienteNoExisteException;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Cliente;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.ClienteRepositorioMySQL;
import com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.UsuarioTransformador;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepositorioMySQL clienteRepositorioMySQL;

    public List<Cliente> consultarClientes(Boolean esActivo) {
        return clienteRepositorioMySQL.consultarClientes(UsuarioTransformador.transformBooleanToString(esActivo));
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepositorioMySQL.crearCliente(cliente);
    }

    public void eliminarCliente(Long idCliente) {
        clienteRepositorioMySQL.eliminarCliente(idCliente);
    }

    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepositorioMySQL.consultarNombreClienteYProyecto(cliente.getNombreCliente(), cliente.getNombreProyecto())
                .map(aa -> clienteRepositorioMySQL.crearCliente(cliente))
                .orElseThrow(() -> new ClienteNoExisteException("No se encontro el cliente con nombre " + cliente.getNombreCliente()));
    }
}
