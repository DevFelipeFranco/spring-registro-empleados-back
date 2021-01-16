package com.registro.empleados.springregistroempleadosback.dominio.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.Autenticacion;

import java.time.Instant;
import java.util.Optional;

public final class UsuarioTransformer {

    public static Optional<Autenticacion> usuarioToAutenticacion(String token, Optional<Usuario> usuario, Long expiracionToken, String recargarToken) {
        return usuario.map(u -> Autenticacion.builder()
                .tokenAutenticacion(token)
                .refreshToken(recargarToken)
                .expiresAt(Instant.now().plusMillis(expiracionToken))
                .usuario(UsuarioTransformer.usuarioSinClave(u))
                .build());

    }

    private static Usuario usuarioSinClave(Usuario usuario) {
        return Usuario.builder()
                .conIdUsuario(usuario.getIdUsuario())
                .conUsuario(usuario.getUsuario())
                .conEmail(usuario.getEmail())
                .conFechaCreacion(usuario.getFechaCreacion())
                .conEstado(usuario.getEstado())
                .conRoles(usuario.getRoles())
                .build();
    }

    public static Usuario usuariOptSinClave(Optional<Usuario> usuario) {
        return usuario.map(UsuarioTransformer::usuarioSinClave).orElse(null);
    }
}
