package com.registro.empleados.springregistroempleadosback.dominio.transformadores;

import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.Autenticacion;

import java.time.LocalDateTime;
import java.util.Optional;

public final class UsuarioTransformer {

    public static Optional<Autenticacion> usuarioToAutenticacion(String token, Optional<Usuario> usuario, Long expiracionToken, String recargarToken) {
        return usuario.map(u -> Autenticacion.builder()
                .tokenAutenticacion(token)
                .refreshToken(recargarToken)
                .expiresAt(LocalDateTime.now().plusSeconds(expiracionToken))
                .usuario(UsuarioTransformer.usuarioSinClave(u))
                .build());

    }

    private static Usuario usuarioSinClave(Usuario usuario) {
        return Usuario.builder()
                .conIdUsuario(usuario.getIdUsuario())
                .conNombres(usuario.getNombres())
                .conApellidos(usuario.getApellidos())
                .conUsuario(usuario.getUsuario())
                .conEmail(usuario.getEmail())
                .conFechaCreacion(usuario.getFechaCreacion())
                .conFechaUltimoIngreso(usuario.getFechaUltimoIngreso())
                .conFechaUltimoIngresoVisualizacion(usuario.getFechaUltimoIngresoVisualizacion())
                .conEstado(usuario.getEstado())
                .conSnNoBloqueado(usuario.getSnNoBloqueado())
                .conImagenPerfilUrl(usuario.getImagenPerfilUrl())
                .conRoles(usuario.getRoles())
                .build();
    }

    public static Usuario usuarioSinClaveNiRoles(Usuario usuario) {
        return Usuario.builder()
                .conIdUsuario(usuario.getIdUsuario())
                .conNombres(usuario.getNombres())
                .conApellidos(usuario.getApellidos())
                .conUsuario(usuario.getUsuario())
                .conEmail(usuario.getEmail())
                .conFechaCreacion(usuario.getFechaCreacion())
                .conFechaUltimoIngreso(usuario.getFechaUltimoIngreso())
                .conFechaUltimoIngresoVisualizacion(usuario.getFechaUltimoIngresoVisualizacion())
                .conEstado(usuario.getEstado())
                .conSnNoBloqueado(usuario.getSnNoBloqueado())
                .conImagenPerfilUrl(usuario.getImagenPerfilUrl())
                .build();
    }

    public static Usuario usuariOptSinClave(Optional<Usuario> usuario) {
        return usuario.map(UsuarioTransformer::usuarioSinClave).orElse(null);
    }
}
