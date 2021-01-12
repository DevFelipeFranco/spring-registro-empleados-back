package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Token;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.TokenEntidad;

import java.util.Optional;

public final class TokenTransformador {

    public static TokenEntidad tokenToTokenEntidad(Token token) {
        return TokenEntidad.builder()
                .idToken(token.getIdToken())
                .token(token.getToken())
                .fechaExpiracion(token.getFechaExpiracion())
                .usuarioEntidad(UsuarioTransformador.usuarioModeloToEntidad(token.getUsuario()))
                .build();
    }

    public static Token tokenEntidadToToken(TokenEntidad tokenEntidad) {
        return Token.builder()
                .conIdToken(tokenEntidad.getIdToken())
                .conToken(tokenEntidad.getToken())
                .conFechaExpiracion(tokenEntidad.getFechaExpiracion())
                .conUsuario(UsuarioTransformador.usuarioEntidadToModel(tokenEntidad.getUsuarioEntidad()))
                .build();
    }

    public static Optional<Token> tokenEntidadOptToTokenOpt(Optional<TokenEntidad> tokenEntidad) {
        return tokenEntidad.map(TokenTransformador::tokenEntidadToToken);
    }
}
