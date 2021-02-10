package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Genero;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.GeneroEntidad;

import java.util.List;
import java.util.stream.Collectors;

public final class GeneroTransformador {

    public static Genero genetoEntidadToModelo(GeneroEntidad generoEntidad) {
        return Genero.builder()
                .idGenero(generoEntidad.getIdGenero())
                .codigoGenero(generoEntidad.getCdGenero())
                .descripcion(generoEntidad.getDescripcion())
                .build();
    }

    public static GeneroEntidad genetoModeloToEntidad(Genero genero) {
        return GeneroEntidad.builder()
                .idGenero(genero.getIdGenero())
                .cdGenero(genero.getCodigoGenero())
                .descripcion(genero.getDescripcion())
                .build();
    }

    public static List<Genero> generosEntidadToModelo(List<GeneroEntidad> generoEntidad) {
        return generoEntidad.stream().map(GeneroTransformador::genetoEntidadToModelo).collect(Collectors.toList());
    }
}
