package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Contrato;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.ContratoEntidad;

import java.util.List;
import java.util.stream.Collectors;

public final class ContratoTransformador {

    public static Contrato contratoEntidadToModelo(ContratoEntidad contratoEntidad) {
        return Contrato.builder()
                .idContrato(contratoEntidad.getIdContrato())
                .fechaIngreso(contratoEntidad.getFechaIngreso())
                .fechaSalida(contratoEntidad.getFechaSalida())
                .documentoContrato(contratoEntidad.getDocumentoContrato())
                .persona(PersonaTransformador.personaEntidadToModelo(contratoEntidad.getPersonaEntidad()))
                .tipoContrato(TipoContratoTransformador.tipoContratoEntidadToModelo(contratoEntidad.getTipoContratoEntidad()))
                .build();
    }

    public static List<Contrato> contratosEntidadToModelo(List<ContratoEntidad> contratosEntidad) {
        return contratosEntidad.stream().map(ContratoTransformador::contratoEntidadToModelo).collect(Collectors.toList());
    }
}
