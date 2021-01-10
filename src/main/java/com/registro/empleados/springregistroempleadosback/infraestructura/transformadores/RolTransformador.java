package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.RolModelo;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.RolEntidad;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class RolTransformador {

    public static RolModelo rolEntidadToModelo(RolEntidad rolEntidad) {
        return RolModelo.builder()
                .conIdRol(rolEntidad.getIdRol())
                .conDescripcion(rolEntidad.getDescripcion())
                .build();
    }

    public static List<RolModelo> listadoUsuarioEntidadToModel(List<RolEntidad> roles) {
        return roles.stream()
                .map(RolTransformador::rolEntidadToModelo)
                .collect(Collectors.toList());
    }
}
