package com.registro.empleados.springregistroempleadosback.aplicacion.fabrica;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoTipoDocumento;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.TipoDocumento;

public final class FabricaTipoDocumento {

    public static TipoDocumento comandoTipoDocumentoToTipoDocumento(ComandoTipoDocumento comandoTipoDocumento) {
        return TipoDocumento.builder()
                .idTipoDocumento(comandoTipoDocumento.getIdTipoDocumento())
                .tipoDocumento(comandoTipoDocumento.getTipoDocumento())
                .descripcion(comandoTipoDocumento.getDescripcion())
                .build();
    }
}
