package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.TipoDocumento;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.TipoDocumentoEntidad;

public final class TipoDocumentoTransformador {

    public static TipoDocumentoEntidad tipoDocumentoToEntidad(TipoDocumento tipoDocumento) {
        return TipoDocumentoEntidad.builder()
                .idTipoDocumento(tipoDocumento.getIdTipoDocumento())
                .tipoDocumento(tipoDocumento.getTipoDocumento())
                .descripcion(tipoDocumento.getDescripcion())
                .build();
    }

    public static TipoDocumento tipoDocumentoEntidadToModelo(TipoDocumentoEntidad tipoDocumentoEntidad) {
        return TipoDocumento.builder()
                .idTipoDocumento(tipoDocumentoEntidad.getIdTipoDocumento())
                .tipoDocumento(tipoDocumentoEntidad.getTipoDocumento())
                .descripcion(tipoDocumentoEntidad.getDescripcion())
                .build();
    }
}
