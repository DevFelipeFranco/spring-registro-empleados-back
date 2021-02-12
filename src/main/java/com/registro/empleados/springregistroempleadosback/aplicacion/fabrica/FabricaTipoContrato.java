package com.registro.empleados.springregistroempleadosback.aplicacion.fabrica;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoTipoContrato;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.TipoContrato;

public final class FabricaTipoContrato {

    public static TipoContrato comandoTipoContratoToModelo(ComandoTipoContrato comandoTipoContrato) {
        return TipoContrato.builder()
                .idTipoContrato(comandoTipoContrato.getIdTipoContrato())
                .tipoContrato(comandoTipoContrato.getTipoContrato())
                .build();
    }
}
