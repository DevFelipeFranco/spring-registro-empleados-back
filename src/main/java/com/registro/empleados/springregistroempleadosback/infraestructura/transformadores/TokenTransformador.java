package com.registro.empleados.springregistroempleadosback.infraestructura.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.RecargarToken;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Token;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.RecargarTokenEntidad;
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

    public static RecargarToken refreshTokenEntidadToRefreshToken(RecargarTokenEntidad recargarTokenEntidad) {
        return RecargarToken.builder()
                .idRecargarToken(recargarTokenEntidad.getIdRecargarToken())
                .token(recargarTokenEntidad.getToken())
                .fechaCreacion(recargarTokenEntidad.getFechaCreacion())
                .build();
    }

    public static Optional<RecargarToken> refreshTokenEntidadOptToTokenOpt(Optional<RecargarTokenEntidad> recargarTokenEntidad) {
        return recargarTokenEntidad.map(TokenTransformador::refreshTokenEntidadToRefreshToken);
    }
}
