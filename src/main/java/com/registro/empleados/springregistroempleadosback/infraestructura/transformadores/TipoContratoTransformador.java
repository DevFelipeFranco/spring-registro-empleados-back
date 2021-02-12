package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.TipoContrato;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.TipoContratoEntidad;

public final class TipoContratoTransformador {

    public static TipoContrato tipoContratoEntidadToModelo(TipoContratoEntidad tipoContratoEntidad) {
        return TipoContrato.builder()
                .idTipoContrato(tipoContratoEntidad.getIdTipoContrato())
                .tipoContrato(tipoContratoEntidad.getTipoContrato())
                .build();
    }

    public static TipoContratoEntidad tipoContratoToEntidad(TipoContrato tipoContrato) {
        return TipoContratoEntidad.builder()
                .idTipoContrato(tipoContrato.getIdTipoContrato())
                .tipoContrato(tipoContrato.getTipoContrato())
                .build();
    }
}
