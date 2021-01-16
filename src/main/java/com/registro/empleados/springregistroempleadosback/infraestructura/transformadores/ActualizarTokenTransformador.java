package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.RecargarToken;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.RecargarTokenEntidad;

public final class ActualizarTokenTransformador {

    public static RecargarTokenEntidad actualizarTokenToEntidad(RecargarToken recargarToken) {
        return RecargarTokenEntidad.builder()
                .idRecargarToken(recargarToken.getIdRecargarToken())
                .token(recargarToken.getToken())
                .fechaCreacion(recargarToken.getFechaCreacion())
                .build();
    }

    public static RecargarToken actualizarTokenEntidadToModelo(RecargarTokenEntidad recargarTokenEntidad) {
        return RecargarToken.builder()
                .idRecargarToken(recargarTokenEntidad.getIdRecargarToken())
                .token(recargarTokenEntidad.getToken())
                .fechaCreacion(recargarTokenEntidad.getFechaCreacion())
                .build();
    }
}
