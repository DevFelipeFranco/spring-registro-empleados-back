package com.registro.empleados.springregistroempleadosback.aplicacion.fabrica;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoGenero;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Genero;

public final class FabricaGenero {

    public static Genero comandoGeneroToModelo(ComandoGenero comandoGenero) {
        return Genero.builder()
                .idGenero(comandoGenero.getIdGenero())
                .codigoGenero(comandoGenero.getCodigoGenero())
                .descripcion(comandoGenero.getDescripcion())
                .build();
    }
}
