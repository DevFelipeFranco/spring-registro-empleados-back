package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Rol;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.RolEntidad;

import java.util.List;
import java.util.stream.Collectors;

public final class RolTransformador {

    public static RolEntidad rolToRolEntidad(Rol rol) {
        return RolEntidad.builder()
                .idRol(rol.getIdRol())
                .descripcion(rol.getDescripcion())
                .build();
    }

    public static List<RolEntidad> rolesToRolesEntidad(List<Rol> roles) {
        return roles.stream()
                .map(RolTransformador::rolToRolEntidad)
                .collect(Collectors.toList());
    }

    public static Rol rolEntidadToRol(RolEntidad rolEntidad) {
        return Rol.builder()
                .conIdRol(rolEntidad.getIdRol())
                .conDescripcion(rolEntidad.getDescripcion())
                .build();
    }

    public static List<Rol> rolesEntidadToRoles(List<RolEntidad> roles) {
        return roles.stream()
                .map(RolTransformador::rolEntidadToRol)
                .collect(Collectors.toList());
    }
}
