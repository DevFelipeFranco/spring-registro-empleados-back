package com.registro.empleados.springregistroempleadosback.aplicacion.fabrica;

import com.registro.empleados.springregistroempleadosback.aplicacion.comando.ComandoContrato;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Contrato;

public final class FabricaContrato {

    public static Contrato comandoContratoToModelo(ComandoContrato comandoContrato) {
        return Contrato.builder()
                .idContrato(comandoContrato.getIdContrato())
                .fechaIngreso(comandoContrato.getFechaIngreso())
                .fechaSalida(comandoContrato.getFechaSalida())
                .documentoContrato(comandoContrato.getDocumentoContrato())
                .persona(FabricaPersona.comandoPersonaToPersona(comandoContrato.getPersona()))
                .tipoContrato(FabricaTipoContrato.comandoTipoContratoToModelo(comandoContrato.getTipoContrato()))
                .build();
    }
}
