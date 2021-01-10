package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.TokenModelo;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.TokenEntidad;

public final class TokenTransformador {

    public static TokenEntidad tokenModeloToEntidad(TokenModelo tokenModelo) {
        return TokenEntidad.builder()
                .idToken(tokenModelo.getIdToken())
                .fechaExpiracion(tokenModelo.getFechaExpiracion())
                .token(tokenModelo.getToken())
                .usuarioEntidad(UsuarioTransformador.usuarioModeloToEntidad(tokenModelo.getUsuarioModelo()))
                .build();
    }

    public static TokenModelo tokenEntidadToModelo(TokenEntidad tokenEntidad) {
        return TokenModelo.builder()
                .conIdToken(tokenEntidad.getIdToken())
                .conFechaExpiracion(tokenEntidad.getFechaExpiracion())
                .conToken(tokenEntidad.getToken())
                .build();
    }
}
