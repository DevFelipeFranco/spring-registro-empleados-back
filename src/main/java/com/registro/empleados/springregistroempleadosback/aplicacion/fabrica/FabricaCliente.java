package com.registro.empleados.springregistroempleadosback.aplicacion.fabrica;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoCliente;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Cliente;

public final class FabricaCliente {

    public static Cliente comandoClienteToModel(ComandoCliente comandoCliente) {
        return Cliente.builder()
                .idCliente(comandoCliente.getIdCliente())
                .nombreCliente(comandoCliente.getNombreCliente())
                .nombreProyecto(comandoCliente.getNombreProyecto())
                .avanceProyecto(comandoCliente.getAvanceProyecto())
                .descripcion(comandoCliente.getDescripcion())
                .cantidadTrabajadores(comandoCliente.getCantidadTrabajadores())
                .estadoProyecto(comandoCliente.getEstadoProyecto())
                .cantidadSprint(comandoCliente.getCantidadSprint())
                .build();
    }
}
