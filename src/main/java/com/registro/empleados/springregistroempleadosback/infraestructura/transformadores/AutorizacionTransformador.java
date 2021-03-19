package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Autorizacion;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.AutorizacionEntidad;

import java.util.List;
import java.util.stream.Collectors;

public final class AutorizacionTransformador {

    public static Autorizacion entidadToModelo(AutorizacionEntidad autorizacionEntidad) {
        return Autorizacion.builder()
                .conIdAutorizacion(autorizacionEntidad.getIdAutorizacion())
                .conAutorizacion(autorizacionEntidad.getAutorizacion())
                .build();
    }

    public static AutorizacionEntidad modeloToEntidad(Autorizacion autorizacion) {
        return AutorizacionEntidad.builder()
                .idAutorizacion(autorizacion.getIdAutorizacion())
                .autorizacion(autorizacion.getAutorizacion())
                .build();
    }

    public static List<Autorizacion> lstEntidadToModelo(List<AutorizacionEntidad> autorizacionesEntidad) {
        return autorizacionesEntidad.stream().map(AutorizacionTransformador::entidadToModelo).collect(Collectors.toList());
    }

    public static List<AutorizacionEntidad> lstModeloToEntidad(List<Autorizacion> autorizacion) {
        return autorizacion.stream().map(AutorizacionTransformador::modeloToEntidad).collect(Collectors.toList());
    }
}
