package com.registro.empleados.springregistroempleadosback.dominio.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.Autenticacion;

import java.util.Optional;

public final class UsuarioTransformer {

    public static Optional<Autenticacion> usuarioToAutenticacion(String token, Optional<Usuario> usuario) {
        return usuario.map(u -> Autenticacion.builder()
                .tokenAutenticacion(token)
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
}
