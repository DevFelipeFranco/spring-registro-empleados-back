package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Cliente;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.ClienteEntidad;

import java.util.List;
import java.util.stream.Collectors;

import static com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.UsuarioTransformador.transformBooleanToString;
import static com.registro.empleados.springregistroempleadosback.infraestructura.transformadores.UsuarioTransformador.transformStringToBoolean;

public final class ClienteTransformador {

    private ClienteTransformador() {

    }

    public static ClienteEntidad modelToEntidad(Cliente cliente) {
        return ClienteEntidad.builder()
                .idCliente(cliente.getIdCliente())
                .nombreCliente(cliente.getNombreCliente())
                .nombreProyecto(cliente.getNombreProyecto())
                .avanceProyecto(cliente.getAvanceProyecto())
                .descripcion(cliente.getDescripcion())
                .cantidadTrabajadores(cliente.getCantidadTrabajadores())
                .snProyectoActivo(transformBooleanToString(cliente.getEstadoProyecto()))
                .build();
    }

    public static Cliente entidadToModelo(ClienteEntidad clienteEntidad) {
        return Cliente.builder()
                .idCliente(clienteEntidad.getIdCliente())
                .nombreCliente(clienteEntidad.getNombreCliente())
                .nombreProyecto(clienteEntidad.getNombreProyecto())
                .avanceProyecto(clienteEntidad.getAvanceProyecto())
                .descripcion(clienteEntidad.getDescripcion())
                .cantidadTrabajadores(clienteEntidad.getCantidadTrabajadores())
                .estadoProyecto(transformStringToBoolean(clienteEntidad.getSnProyectoActivo()))
                .build();
    }

    public static List<Cliente> entidadToModelo(List<ClienteEntidad> clientesEntidad) {
        return clientesEntidad.stream().map(ClienteTransformador::entidadToModelo)
                .collect(Collectors.toList());
    }
}
